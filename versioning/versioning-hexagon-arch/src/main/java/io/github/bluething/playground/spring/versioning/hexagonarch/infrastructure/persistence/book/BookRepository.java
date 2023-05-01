package io.github.bluething.playground.spring.versioning.hexagonarch.infrastructure.persistence.book;

import java.util.List;

public interface BookRepository {
    BookDto getBook(String isbn);
    List<BookDto> getBooks();
    void deleteBook(String isbn);
    BookDto addBook(BookDto book);
}
