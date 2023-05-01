package io.github.bluething.playground.spring.versioning.hexagonarch.domain.book;

import java.util.List;

public interface BookRestPort {
    Book getBook(String isbn);
    List<Book> getBooks();
    void deleteBook(String isbn);
    Book addBook(Book book);
}
