package de.workshops.bookshelf.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record BookSearchRequest(@NotBlank String author,
                         @Size(min = 3) String title) {}

