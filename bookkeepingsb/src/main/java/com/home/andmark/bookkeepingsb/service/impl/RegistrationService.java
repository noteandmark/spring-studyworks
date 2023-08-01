package com.home.andmark.bookkeepingsb.service.impl;

import com.home.andmark.bookkeepingsb.dto.PersonDTO;
import com.home.andmark.bookkeepingsb.model.Person;
import com.home.andmark.bookkeepingsb.repository.PersonsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RegistrationService {
    private final PersonsRepository personsRepository;
    private final ModelMapper mapper;

    @Autowired
    public RegistrationService(PersonsRepository personsRepository, ModelMapper mapper) {
        this.personsRepository = personsRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void register(PersonDTO personDTO) {
        personDTO.setCreatedAt(new Date());
        personsRepository.save(mapper.map(personDTO, Person.class));
    }

}
