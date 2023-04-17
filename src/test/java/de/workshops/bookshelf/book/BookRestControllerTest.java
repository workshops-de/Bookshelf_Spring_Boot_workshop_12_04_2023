package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class BookRestControllerTest {

    @Autowired
    BookRestController controller;

    @Test
    void shouldGetAllBooks() {
        final var allBooks = controller.getAllBooks();

        assertThat(allBooks).hasSize(3);
    }

    @Test
    void shouldGetBookByIsbn() {
        final var book = controller.getByIsbn("978-0201633610");

        assertThat(book.getBody().getTitle()).isEqualTo("Design Patterns");
    }
    @Test
    void shouldGetBookByIsbn_throwsException() {
        assertThatThrownBy(() -> controller.getByIsbn("unknown"))
                .isInstanceOf(RuntimeException.class);
    }
}