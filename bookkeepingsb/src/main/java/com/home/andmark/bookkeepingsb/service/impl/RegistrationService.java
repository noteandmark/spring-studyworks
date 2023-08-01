package com.home.andmark.bookkeepingsb.service.impl;

import com.home.andmark.bookkeepingsb.dto.PersonDTO;
import com.home.andmark.bookkeepingsb.model.Person;
import com.home.andmark.bookkeepingsb.repository.PersonsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RegistrationService {
    private final PersonsRepository personsRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonsRepository personsRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.personsRepository = personsRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(PersonDTO personDTO) {
        String encode = passwordEncoder.encode(personDTO.getPassword());

        personDTO.setCreatedAt(new Date());
        personDTO.setPassword(encode);

        personsRepository.save(mapper.map(personDTO, Person.class));
    }

}
