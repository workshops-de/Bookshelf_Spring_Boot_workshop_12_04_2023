package de.workshops.bookshelf.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface BookJpaRepository extends JpaRepository<Book, Integer> {

    Book findByIsbn(String isbn);

    Book findByAuthorContaining(String author);

    List<Book> findByAuthorContainingOrTitleContaining(String author, String title);

    // JPQL
    @Query("select b from Book b where b.author like ?1 or b.title like ?2")
    List<Book> findBySearch(String author, String title);



}
