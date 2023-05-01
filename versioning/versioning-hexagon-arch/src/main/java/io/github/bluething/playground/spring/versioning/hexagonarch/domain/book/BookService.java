package io.github.bluething.playground.spring.versioning.hexagonarch.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class BookService implements BookRestPort {

    private final BookPersistencePort persistencePort;

    @Override
    public Book getBook(String isbn) {
        return persistencePort.getBook(isbn);
    }

    @Override
    public List<Book> getBooks() {
        return persistencePort.getBooks();
    }

    @Override
    public void deleteBook(String isbn) {
        persistencePort.deleteBook(isbn);
    }

    @Override
    public Book addBook(Book book) {
        return persistencePort.addBook(book);
    }
}
