package io.github.bluething.playground.spring.callicoderuploaddownloadrest.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage")
public record StorageProperties(String location) {
}
