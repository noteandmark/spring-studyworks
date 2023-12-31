package com.home.andmark.bookkeepingsb.repository;

import com.home.andmark.bookkeepingsb.model.Book;
import com.home.andmark.bookkeepingsb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitle(String bookTitle);

    List<Book> findByOwner(Person owner);

    @Query("SELECT COUNT(b) FROM Book b")
    int countAllBooks();

    List<Book> findByTitleStartingWith(String titleInitials);
}
