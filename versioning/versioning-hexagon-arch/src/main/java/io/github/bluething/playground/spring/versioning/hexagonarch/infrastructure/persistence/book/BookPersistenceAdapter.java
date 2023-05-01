package io.github.bluething.playground.spring.versioning.hexagonarch.infrastructure.persistence.book;

import io.github.bluething.playground.spring.versioning.hexagonarch.domain.book.Book;
import io.github.bluething.playground.spring.versioning.hexagonarch.domain.book.BookPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class BookPersistenceAdapter implements BookPersistencePort {

    private final BookRepository repository;

    @Override
    public Book getBook(String isbn) {
        BookDto dto = repository.getBook(isbn);
        return new Book(dto.isbn(), dto.author(), dto.title());
    }

    @Override
    public List<Book> getBooks() {
        List<BookDto> dtos = repository.getBooks();
        return dtos.stream()
                .map(dto -> new Book(dto.isbn(), dto.author(), dto.title()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(String isbn) {
        repository.deleteBook(isbn);
    }

    @Override
    public Book addBook(Book book) {
        BookDto dto = repository.addBook(new BookDto(book.isbn(), book.author(), book.title()));
        return new Book(dto.isbn(), dto.author(), dto.title());
    }
}
