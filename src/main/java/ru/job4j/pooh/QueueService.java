package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueService implements Service {
    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Req in = new Req(req.httpRequestType(), req.getPoohMode(),
                req.getSourceName(), req.getParam());
        String status = null;
        String text = null;
        queue.putIfAbsent(in.getSourceName(), new ConcurrentLinkedQueue<String>());
        if (req.httpRequestType().equals("POST")) {
            queue.get(req.getSourceName()).offer(req.getParam());
            text = in.getParam();
            status = "200";
        }
        if (req.httpRequestType().equals("GET")) {
            text = queue.get(req.getSourceName()).poll();
            status = text == null ? "204" : "200";
        }

        return new Resp(text, status);
    }

}
