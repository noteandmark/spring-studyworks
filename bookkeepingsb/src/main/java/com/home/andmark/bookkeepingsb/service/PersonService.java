package com.home.andmark.bookkeepingsb.service;

import com.home.andmark.bookkeepingsb.dto.BookDTO;
import com.home.andmark.bookkeepingsb.dto.PersonDTO;

import java.util.List;

public interface PersonService extends AbstractService<PersonDTO> {
    List<PersonDTO> findAllDao();

    List<BookDTO> getBooksByPersonId(int id);
}
