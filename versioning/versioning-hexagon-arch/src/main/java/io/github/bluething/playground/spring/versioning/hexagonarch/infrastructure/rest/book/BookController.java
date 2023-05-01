package io.github.bluething.playground.spring.versioning.hexagonarch.infrastructure.rest.book;

import io.github.bluething.playground.spring.versioning.hexagonarch.domain.book.Book;
import io.github.bluething.playground.spring.versioning.hexagonarch.domain.book.BookRestPort;
import io.github.bluething.playground.spring.versioning.hexagonarch.infrastructure.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRestPort restPort;

    @GetMapping("/v1/book/{isbn}")
    @Deprecated
    BookPayload getBook(@PathVariable String isbn) {
        Book book = restPort.getBook(isbn);
        return new BookPayload(book.isbn(), book.author(), book.title());
    }

    @GetMapping("/v2/book/{isbn}")
    RestResponse getBookV2(@PathVariable String isbn) {
        Book book = restPort.getBook(isbn);
        return new RestResponse(new BookPayload(book.isbn(), book.author(), book.title()));
    }

    @GetMapping(value = "/book", headers = "X-API-VERSION=1")
    @Deprecated
    List<BookPayload> getBook() {
        List<Book> books = restPort.getBooks();
        return books.stream()
                .map(book -> new BookPayload(book.isbn(), book.author(), book.title()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/book", headers = "X-API-VERSION=2")
    RestResponse getBookV2() {
        List<Book> books = restPort.getBooks();
        return new RestResponse(books.stream()
                .map(book -> new BookPayload(book.isbn(), book.author(), book.title()))
                .collect(Collectors.toList()));
    }

    @PostMapping(value = "/book",
            produces = "application/vnd.service.book-v1+json",
            consumes = "application/vnd.service.book-v1+json")
    @Deprecated
    BookPayload addBook(@RequestBody BookPayload payload) {
        Book book = restPort.addBook(new Book(payload.isbn(), payload.author(), payload.title()));
        return new BookPayload(book.isbn(), book.author(), book.title());
    }

    @PostMapping(value = "/book",
            produces = "application/vnd.service.book-v2+json",
            consumes = "application/vnd.service.book-v2+json")
    RestResponse getBookV2(@RequestBody BookPayload payload) {
        Book book = restPort.addBook(new Book(payload.isbn(), payload.author(), payload.title()));
        return new RestResponse(new BookPayload(book.isbn(), book.author(), book.title()));
    }

}