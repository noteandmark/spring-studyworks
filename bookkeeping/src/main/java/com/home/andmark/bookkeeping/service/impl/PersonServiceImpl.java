package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.dao.impl.JdbcBookDAOImpl;
import com.home.andmark.bookkeeping.dao.impl.JdbcPersonDAOImpl;
import com.home.andmark.bookkeeping.dto.PersonDTO;
import com.home.andmark.bookkeeping.model.Book;
import com.home.andmark.bookkeeping.model.Person;
import com.home.andmark.bookkeeping.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final JdbcPersonDAOImpl personDAO;
    private final JdbcBookDAOImpl bookDAO;
    private final ModelMapper mapper;

    @Autowired
    public PersonServiceImpl(JdbcPersonDAOImpl personDAO, JdbcBookDAOImpl bookDAO, ModelMapper mapper) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.mapper = mapper;
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = mapper.map(personDTO, Person.class);
        return mapper.map(personDAO.save(person), PersonDTO.class);
    }

    @Override
    public PersonDTO read(int id) {
        Person person = personDAO.read(id);
        List<Book> books = bookDAO.getBooksByPersonId(id);
        person.setBooks(books);
        PersonDTO personDTO = mapper.map(person, PersonDTO.class);
        return personDTO;
    }

    @Override
    public List<PersonDTO> readAll() {
        List<Person> people = personDAO.readAll();
        return mapListOfEntityToDTO(people);
    }

    @Override
    public PersonDTO update(int id, PersonDTO personDTO) {
        Person updated = personDAO.update(id, mapper.map(personDTO, Person.class));
        return mapper.map(updated, PersonDTO.class);
    }

    @Override
    public int delete(int id) {
        return personDAO.delete(id);
    }

    private List<PersonDTO> mapListOfEntityToDTO(List<Person> all) {
        return all.stream().map(person -> mapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }
}
