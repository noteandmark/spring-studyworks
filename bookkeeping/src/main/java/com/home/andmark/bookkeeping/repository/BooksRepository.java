package com.home.andmark.bookkeeping.repository;

import com.home.andmark.bookkeeping.model.Book;
import com.home.andmark.bookkeeping.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitle(String bookTitle);

    List<Book> findByOwner(Person owner);
}
