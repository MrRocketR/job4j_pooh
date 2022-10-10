package ru.job4j.pooh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReqTest {

    @Test
    public void whenQueueModePostMethod() {
        String ls = System.lineSeparator();
        String content = "POST /queue/weather HTTP/1.1" + ls
                + "Host: localhost:9000" + ls
                + "User-Agent: curl/7.72.0" + ls
                + "Accept: */*" + ls
                + "Content-Length: 14" + ls
                + "Content-Type: application/x-www-form-urlencoded" + ls
                + "" + ls + "temperature=18" + ls;
        Req req = Req.of(content);
        Assertions.assertEquals("POST", req.httpRequestType());
        Assertions.assertEquals("queue", req.getPoohMode());
        Assertions.assertEquals("weather", req.getSourceName());
        Assertions.assertEquals("temperature=18", req.getParam());
    }

    @Test
    public void whenQueueModeGetMethod() {
        String ls = System.lineSeparator();
        String content = "GET /queue/weather HTTP/1.1" + ls
                + "Host: localhost:9000" + ls
                + "User-Agent: curl/7.72.0" + ls
                + "Accept: */*" + ls + ls + ls;
        Req req = Req.of(content);
        Assertions.assertEquals("GET", req.httpRequestType());
        Assertions.assertEquals("queue", req.getPoohMode());
        Assertions.assertEquals("weather", req.getSourceName());
        Assertions.assertEquals("", req.getParam());
    }

    @Test
    public void whenTopicModePostMethod() {
        String ls = System.lineSeparator();
        String content = "POST /topic/weather HTTP/1.1"
                + ls + "Host: localhost:9000" + ls
                + "User-Agent: curl/7.72.0" + ls
                + "Accept: */*" + ls
                + "Content-Length: 14" + ls
                + "Content-Type: application/x-www-form-urlencoded" + ls
                + "" + ls + "temperature=18" + ls;
        Req req = Req.of(content);
        Assertions.assertEquals("POST", req.httpRequestType());
        Assertions.assertEquals("topic", req.getPoohMode());
        Assertions.assertEquals("weather", req.getSourceName());
        Assertions.assertEquals("temperature=18", req.getParam());
    }

    @Test
    public void whenTopicModeGetMethod() {
        String ls = System.lineSeparator();
        String content = "GET /topic/weather/client407 HTTP/1.1" + ls
                + "Host: localhost:9000" + ls
                + "User-Agent: curl/7.72.0" + ls
                + "Accept: */*" + ls + ls + ls;
        Req req = Req.of(content);
        Assertions.assertEquals("GET", req.httpRequestType());
        Assertions.assertEquals("topic", req.getPoohMode());
        Assertions.assertEquals("weather", req.getSourceName());
        Assertions.assertEquals("client407", req.getParam());
    }
}


