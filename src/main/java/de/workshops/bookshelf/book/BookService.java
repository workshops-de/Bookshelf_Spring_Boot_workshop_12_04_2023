package de.workshops.bookshelf.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
//    private final BookRepository repository;
//
//    public BookService(BookRepository repository) {
//        this.repository = repository;
//    }
    private final BookJdbcRepository repository;

    public BookService(BookJdbcRepository repository) {
        this.repository = repository;
    }

    List<Book> getAllBooks() {
        return repository.findAllBooks();
    }

    Book getByIsbn(String isbn) {
        return repository.findAllBooks().stream()
                .filter(book -> isbn.equals(book.getIsbn()))
                .findFirst()
                .orElseThrow();
    }

    Book getByAuthor(String author) {
        return repository.findAllBooks().stream()
                .filter(book -> book.getAuthor().contains(author))
                .findFirst()
                .orElseThrow(() -> new BookException("Bücher dieses Autors habe ich nicht."));
    }

    List<Book> searchBooks(BookSearchRequest searchRequest) {
        return repository.findAllBooks().stream()
                .filter(book -> book.getAuthor().contains(searchRequest.author())
                        || book.getTitle().contains(searchRequest.title()))
                .toList();
    }

    Book createBook(Book book) {
        repository.saveBook(book);
        return book;
    }
}
