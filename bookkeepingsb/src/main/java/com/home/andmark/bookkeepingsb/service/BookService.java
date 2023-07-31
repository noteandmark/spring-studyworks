package com.home.andmark.bookkeepingsb.service;

import com.home.andmark.bookkeepingsb.dto.BookDTO;

import java.util.List;

public interface BookService extends AbstractService<BookDTO> {
    void assignBookToPerson(int bookId, int personId);

    void releaseBook(int id);

    List<BookDTO> findAllPaginated(int page, int booksPerPage);

    int countAllBooks();

    List<BookDTO> findAllSortedByYear();

    List<BookDTO> searchByTitle(String titleInitials);
}
