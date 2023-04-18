package de.workshops.bookshelf.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceUsingJpaRepository {

    private final BookJpaRepository repository;

    public BookServiceUsingJpaRepository(BookJpaRepository repository) {
        this.repository = repository;
    }

    List<Book> getAllBooks() {
        return repository.findAll();
    }

    Book getByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    Book getByAuthor(String author) {
        return repository.findByAuthorContaining(author);
    }

    List<Book> searchBooks(BookSearchRequest searchRequest) {
        return repository.findByAuthorContainingOrTitleContaining(searchRequest.author(), searchRequest.title());
    }

    Book createBook(Book book) {
        return repository.save(book);
    }

}
