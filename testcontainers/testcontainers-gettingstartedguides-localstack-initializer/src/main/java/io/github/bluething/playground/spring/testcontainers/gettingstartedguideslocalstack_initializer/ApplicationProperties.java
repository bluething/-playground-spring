package io.github.bluething.playground.spring.testcontainers.gettingstartedguideslocalstack_initializer;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record ApplicationProperties(String queue, String bucket) {
}
