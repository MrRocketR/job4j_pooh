package ru.job4j.pooh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TopicServiceTest {

    @Test
    public void whenTopic() {
        TopicService topicService = new TopicService();
        String paramForPublisher = "temperature=18";
        String paramForSubscriber1 = "client407";

        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );

        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );

        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );

        Assertions.assertEquals("temperature=18", result1.text());
    }
}