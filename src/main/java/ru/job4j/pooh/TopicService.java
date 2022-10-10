package ru.job4j.pooh;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {

    private ConcurrentHashMap<String, ConcurrentHashMap
            <String, ConcurrentLinkedQueue<String>>> topicMap
            = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String status = "501";
        String text = "";
        if ("GET".equals(req.httpRequestType())) {
            topicMap.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>());
            topicMap.get(req.getSourceName()).putIfAbsent(req.getParam(),
                    new ConcurrentLinkedQueue<>());
            text = topicMap.get(req.getSourceName()).get(req.getParam()).poll();
            status = "200";
            return new Resp(text, status);
        } else if ("POST".equals(req.httpRequestType())) {
            ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> topic = topicMap.
                    get(req.getSourceName());
            topic.forEach((key, value) -> value.add(req.getParam()));
            return new Resp(req.getParam(), "200");
        }
        return new Resp(text, status);
    }
}
