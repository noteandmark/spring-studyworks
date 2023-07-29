package com.home.andmark.bookkeeping.service;

import com.home.andmark.bookkeeping.dto.BookDTO;
import com.home.andmark.bookkeeping.dto.PersonDTO;

public interface BookService extends AbstractService<BookDTO> {
    void assignBookToPerson(BookDTO bookDTO, PersonDTO personDTO);
    void releaseBook(int id);
}
