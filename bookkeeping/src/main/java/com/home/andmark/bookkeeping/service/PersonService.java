package com.home.andmark.bookkeeping.service;

import com.home.andmark.bookkeeping.dto.PersonDTO;

import java.util.List;

public interface PersonService extends AbstractService<PersonDTO>{
     List<PersonDTO> findAllDao();
}
