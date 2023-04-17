package de.workshops.bookshelf.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerMockMvcAndMockedServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BookService bookServiceMock;

    @Captor
    ArgumentCaptor<String> isbnCaptor;

    @Test
    void shouldGetAllBooks() throws Exception {
        when(bookServiceMock.getAllBooks()).thenReturn(List.of(new Book(), new Book(), new Book()));

        final var mvcResult = mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andReturn();

        final var payload = mvcResult.getResponse().getContentAsString();

        List<Book> books = mapper.readValue(payload, new TypeReference<>() {});

        assertThat(books).hasSize(3);
    }

    @Test
    void shouldGetBookByIsbn_validIsbn() throws Exception {
        when(bookServiceMock.getByIsbn(isbnCaptor.capture())).thenReturn(new Book());

        mockMvc.perform(get("/book/1234567890"))
                .andExpect(status().isOk());

        final var value = isbnCaptor.getValue();
        assertThat(value).isEqualTo("1234567890");
    }

    @Test
    void shouldGetBookByIsbn_invalidIsbn() throws Exception {
        mockMvc.perform(get("/book/123"))
                .andExpect(status().isUnprocessableEntity());
    }
}
