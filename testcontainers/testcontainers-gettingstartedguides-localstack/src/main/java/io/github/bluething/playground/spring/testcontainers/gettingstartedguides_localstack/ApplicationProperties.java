package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_localstack;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record ApplicationProperties(String queue, String bucket) {
}
