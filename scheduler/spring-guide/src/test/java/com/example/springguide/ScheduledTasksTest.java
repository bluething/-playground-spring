package com.example.springguide;

import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
class ScheduledTasksTest {
    @SpyBean
    ScheduledTasks scheduledTasks;

    @Test
    void reportCurrentTimeTest() {
        Awaitility.await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
            Mockito.verify(scheduledTasks, Mockito.atLeast(2)).reportCurrentTime();
        });
    }
}