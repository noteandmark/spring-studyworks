package com.home.andmark.bookkeepingsb.service.impl;

import com.home.andmark.bookkeepingsb.model.Person;
import com.home.andmark.bookkeepingsb.repository.PersonsRepository;
import com.home.andmark.bookkeepingsb.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonsRepository personsRepository;

    public PersonDetailsService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = personsRepository.findBySurname(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new PersonDetails(person.get());
    }
}
