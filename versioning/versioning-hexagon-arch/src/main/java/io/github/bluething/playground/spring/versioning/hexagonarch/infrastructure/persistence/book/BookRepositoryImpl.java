package io.github.bluething.playground.spring.versioning.hexagonarch.infrastructure.persistence.book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
class BookRepositoryImpl implements BookRepository {
    @Override
    public BookDto getBook(String isbn) {
        return new BookDto(isbn, "This is the author", "This is a title");
    }

    @Override
    public List<BookDto> getBooks() {
        List<BookDto> books = new ArrayList<>();
        books.add(new BookDto("978-6-2021-1578-0", "This is the author", "This is a title"));
        books.add(new BookDto("978-0-4546-1715-3", "This is the author", "This is a title"));
        return books;
    }

    @Override
    public void deleteBook(String isbn) {

    }

    @Override
    public BookDto addBook(BookDto book) {
        return book;
    }
}