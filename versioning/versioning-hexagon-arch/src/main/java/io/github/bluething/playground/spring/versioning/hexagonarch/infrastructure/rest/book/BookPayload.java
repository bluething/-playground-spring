package io.github.bluething.playground.spring.versioning.hexagonarch.infrastructure.rest.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookPayload(@JsonProperty("isbn") String isbn,
                          @JsonProperty("author") String author,
                          @JsonProperty("title") String title) {
}
