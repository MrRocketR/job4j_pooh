package ru.job4j.pooh;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueService implements Service {
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String status = "501";
        String text = "";
        if ("POST".equals(req.httpRequestType())) {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<String>());
            queue.get(req.getSourceName()).offer(req.getParam());
            text = req.getParam();
            status = "200";
        } else if ("GET".equals(req.httpRequestType())) {
            text = Optional.ofNullable(queue.get(req.getSourceName()).poll()).orElse("");
            status = text.equals("") ? "204" : "200";
        }

        return new Resp(text, status);
    }

}
