package de.workshops.bookshelf.book;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {

    private final BookService service;

    public BookRestController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("{isbn}")
    public ResponseEntity<Book> getByIsbn(@PathVariable @Size(min=10, max=14) String isbn) {
        final var book = service.getByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @GetMapping(params = "author")
    public Book getByAuthor(@RequestParam String author) {
        return service.getByAuthor(author);
    }

    @PostMapping("/search")
    public List<Book> searchBooks(@RequestBody @Valid BookSearchRequest searchRequest) {
        return service.searchBooks(searchRequest);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
//        var createdBook = service.createBook(book);
        return ResponseEntity.ok(book);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolations(ConstraintViolationException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
}
