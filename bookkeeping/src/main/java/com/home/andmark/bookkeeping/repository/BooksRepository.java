package com.home.andmark.bookkeeping.repository;

import com.home.andmark.bookkeeping.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
