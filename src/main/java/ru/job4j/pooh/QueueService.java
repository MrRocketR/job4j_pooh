package ru.job4j.pooh;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

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
            text = queue.getOrDefault(req.getSourceName(),
                    new ConcurrentLinkedQueue<>()).poll();
            text = text == null ? "" : text;
            status = text.equals("") ? "204" : "200";
        }
        return new Resp(text, status);
    }

}
