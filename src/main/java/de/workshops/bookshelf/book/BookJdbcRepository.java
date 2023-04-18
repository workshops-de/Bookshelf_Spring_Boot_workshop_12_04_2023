package de.workshops.bookshelf.book;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class BookJdbcRepository {

    private final JdbcTemplate template;

    BookJdbcRepository(JdbcTemplate template) {
        this.template = template;
    }

    List<Book> findAllBooks() {
        String sql = "SELECT * FROM book";

        return template.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    void saveBook(Book book) {
        String sql = "INSERT INTO book (isbn, title, author, description) VALUES (?, ?, ?, ?)";

        template.update(sql, book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription());
    }
}
