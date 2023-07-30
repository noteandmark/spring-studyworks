package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.dao.PersonDAO;
import com.home.andmark.bookkeeping.dto.BookDTO;
import com.home.andmark.bookkeeping.dto.PersonDTO;
import com.home.andmark.bookkeeping.model.Book;
import com.home.andmark.bookkeeping.model.Person;
import com.home.andmark.bookkeeping.repository.PersonsRepository;
import com.home.andmark.bookkeeping.service.PersonService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonsRepository personsRepository;
    private final PersonDAO personDAO;
    private final ModelMapper mapper;

    public PersonServiceImpl(PersonsRepository personsRepository, PersonDAO personDAO, ModelMapper mapper) {
        this.personsRepository = personsRepository;
        this.personDAO = personDAO;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void save(PersonDTO personDTO) {
        personDTO.setCreatedAt(new Date());
        personsRepository.save(mapper.map(personDTO, Person.class));
    }

    @Override
    public PersonDTO findOne(int id) {
        PersonDTO foundPerson = mapper.map
                (personsRepository.findById(id).get(), PersonDTO.class);
        return foundPerson;
    }

    @Override
    public List<PersonDTO> findAll() {
        return mapListOfEntityToDTO(personsRepository.findAll());
    }

    @Override
    public List<PersonDTO> findAllDao() {
        return mapListOfEntityToDTO(personDAO.readAllPersons());
    }

    public List<BookDTO> getBooksByPersonId(int id) {
        Optional<Person> person = personsRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().forEach(book -> {
                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                // 864000000 milliseconds = 10 days
                if (diffInMillies > 864000000)
                    book.setOverdue(true);
            });

            return mapListOfBookToDTO(person.get().getBooks());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public void update(int id, PersonDTO updatedPersonDTO) {
        updatedPersonDTO.setId(id);
        personsRepository.save(mapper.map(updatedPersonDTO, Person.class));
    }

    @Override
    @Transactional
    public void delete(int id) {
        personsRepository.deleteById(id);
    }

    private List<PersonDTO> mapListOfEntityToDTO(List<Person> all) {
        return all.stream().map(person -> mapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }

    private List<BookDTO> mapListOfBookToDTO(List<Book> all) {
        return all.stream().map(book -> mapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

}
