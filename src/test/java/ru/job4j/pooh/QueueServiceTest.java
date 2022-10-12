package ru.job4j.pooh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueueServiceTest {

    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );

        Assertions.assertEquals("temperature=18", result.text());

    }

    @Test
    public void whenEmptyQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        Assertions.assertEquals("200", result.status());

    }

    @Test
    public void whenQueueNotAvailableThenNPE() {
        QueueService queueService = new QueueService();

        Resp resp = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        Assertions.assertEquals("", (resp.text()));
    }

}
