package de.workshops.bookshelf.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRestControllerJUnitTest {

    @Mock
    BookService bookServiceMock;

    @InjectMocks
    BookRestController bookRestController;

    @Test
    void shouldGetBooks() {
        final var expectedBooks = List.of(new Book(), new Book(), new Book());
        when(bookServiceMock.getAllBooks()).thenReturn(expectedBooks);

        final var allBooks = bookRestController.getAllBooks();

        assertThat(allBooks).hasSize(3);
    }
}
