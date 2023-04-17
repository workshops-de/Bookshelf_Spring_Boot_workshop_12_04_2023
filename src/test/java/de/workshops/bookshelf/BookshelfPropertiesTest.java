package de.workshops.bookshelf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookshelfPropertiesTest {

    @Autowired
    BookshelfProperties bookshelfProperties;
    @Test
    void shouldReadBookshelfProperties() {
        assertThat(bookshelfProperties.getNumberBooks()).isEqualTo(1000);
        assertThat(bookshelfProperties.getOwner()).isEqualTo("Birgit");
    }

}