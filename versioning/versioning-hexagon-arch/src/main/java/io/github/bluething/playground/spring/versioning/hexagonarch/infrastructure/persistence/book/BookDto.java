package io.github.bluething.playground.spring.versioning.hexagonarch.infrastructure.persistence.book;

public record BookDto(String isbn,
                      String author,
                      String title) {
}
