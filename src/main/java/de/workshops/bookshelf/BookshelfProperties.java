package de.workshops.bookshelf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bookshelf")
public class BookshelfProperties {
    private int numberBooks;
    private String owner;

    public int getNumberBooks() {
        return numberBooks;
    }

    public void setNumberBooks(int numberBooks) {
        this.numberBooks = numberBooks;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
