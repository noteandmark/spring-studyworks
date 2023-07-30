package com.home.andmark.bookkeeping.service;

import com.home.andmark.bookkeeping.dto.BookDTO;
import com.home.andmark.bookkeeping.dto.PersonDTO;

import java.util.List;

public interface BookService extends AbstractService<BookDTO> {
    void assignBookToPerson(int bookId, int personId);
    void releaseBook(int id);
    List<BookDTO> findAllPaginated(int page, int booksPerPage);

    int countAllBooks();
}
