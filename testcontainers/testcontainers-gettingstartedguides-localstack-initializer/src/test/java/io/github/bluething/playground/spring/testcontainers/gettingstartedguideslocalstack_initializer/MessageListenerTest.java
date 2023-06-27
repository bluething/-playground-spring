package io.github.bluething.playground.spring.testcontainers.gettingstartedguideslocalstack_initializer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.Duration;
import java.util.UUID;

import static org.awaitility.Awaitility.await;

@ContextConfiguration(initializers = TestcontainersInitializer.class)
@SpringBootTest
class MessageListenerTest {

    @Autowired
    StorageService storageService;

    @Autowired
    MessageSender publisher;

    @Autowired
    ApplicationProperties properties;

    @Test
    void shouldHandleMessageSuccessfully() {
        Message message = new Message(UUID.randomUUID(), "Hello World");
        publisher.publish("APP_QUEUE", message);

        await().pollInterval(Duration.ofSeconds(2))
                .atMost(Duration.ofSeconds(10))
                .ignoreExceptions()
                .untilAsserted(() -> {
                    String msg = storageService.downloadAsString(
                            properties.bucket(), message.uuid().toString());
                    Assertions.assertEquals("Hello World", msg);
                });
    }
}
