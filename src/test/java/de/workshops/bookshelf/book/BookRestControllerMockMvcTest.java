package de.workshops.bookshelf.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void shouldGetAllBooks() throws Exception {
        final var mvcResult = mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andReturn();

        final var payload = mvcResult.getResponse().getContentAsString();

        List<Book> books = mapper.readValue(payload, new TypeReference<>() {
        });

        assertThat(books).hasSize(3);
    }

    @Test
    void shouldGetBookByIsbn_invalidIsbn() throws Exception {
        mockMvc.perform(get("/book/123"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldCreateBook() throws Exception {
        String bookToAdd = """
                {
                	"title": "My first book",
                	"description": "Wish I had written it much earlier",
                	"author": "Birgit Kratz",
                	"isbn": "211-1111111"
                }""";
        final var mvcResult = mockMvc.perform(post("/book").content(bookToAdd)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}
