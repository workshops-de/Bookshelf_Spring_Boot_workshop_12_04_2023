package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PortTest {

    @Value("${server.port}")
    private int port;

    @Test
    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'prod'}", loadContext = true)
    void testProPort() {
        assertThat(port).isEqualTo(8090);
    }
}
