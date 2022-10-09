package ru.job4j.pooh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QueueServiceTest {


    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        /* Добавляем данные в очередь weather. Режим queue */
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        /* Забираем данные из очереди weather. Режим queue */
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );

      Assertions.assertEquals("temperature=18", result.text());

    }
}
