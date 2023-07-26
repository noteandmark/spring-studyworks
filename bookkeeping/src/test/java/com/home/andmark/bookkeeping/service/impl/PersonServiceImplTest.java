package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.config.SpringConfig;
import com.home.andmark.bookkeeping.dao.impl.JdbcBookDAOImpl;
import com.home.andmark.bookkeeping.dao.impl.JdbcPersonDAOImpl;
import com.home.andmark.bookkeeping.dto.PersonDTO;
import com.home.andmark.bookkeeping.model.Book;
import com.home.andmark.bookkeeping.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class})
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    private JdbcPersonDAOImpl personDAO;
    @Mock
    private JdbcBookDAOImpl bookDAO;
    @Mock
    private ModelMapper mapper;

    private PersonDTO personDTO;
    private Person person;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        personDTO = new PersonDTO();
        person = new Person();

        Mockito.lenient().when(mapper.map(personDTO, Person.class)).thenReturn(person);
        Mockito.lenient().when(mapper.map(person, PersonDTO.class)).thenReturn(personDTO);

        personDTO.setId(5);
        personDTO.setName("Tati");
        personDTO.setPatronymic("Mich");
        personDTO.setSurname("Koz");
        personDTO.setBirthday(1988);
    }

    @Test
    void save() {
        when(personDAO.save(any())).thenReturn(new Person());
        personService.save(personDTO);
        verify(personDAO, only()).save(any(Person.class));
    }

    @Test
    void read() {
        personDTO = new PersonDTO();
        person = new Person();

        person.setId(5);
        person.setName("Tati");
        person.setPatronymic("Mich");
        person.setSurname("Koz");
        person.setBirthday(1988);

        List<Book> books = new ArrayList<>();
        books.add(new Book(10,5,"New","Aut",1990));
        books.add(new Book(11,5,"New2","Aut2",1991));

        when(personDAO.read(anyInt())).thenReturn(person);
        when(bookDAO.getBooksByPersonId(anyInt())).thenReturn(books);

        PersonDTO expectedPersonDTO = personService.read(5);
        when(mapper.map(person, PersonDTO.class)).thenReturn(expectedPersonDTO);

        verify(personDAO, times(1)).read(5);
        verify(bookDAO, times(1)).getBooksByPersonId(5);

        PersonDTO result = personService.read(5);

        assertEquals(expectedPersonDTO, result);
    }

    @Test
    void findAll() {
        List<Person> all = new ArrayList<>();
        all.add(person);
        when(personDAO.readAll()).thenReturn(all);
        personService.readAll();
        verify(personDAO, only()).readAll();
    }

    @Test
    void update() {
        personDTO.setName("New");
        personDTO.setSurname("Person");
        personService.update(personDTO.getId(),personDTO);
        verify(personDAO,times(1)).update(personDTO.getId(),person);
    }

    @Test
    void delete() {
        person.setId(5);
        personService.delete(personDTO.getId());
        verify(personDAO,times(1)).delete(person.getId());
    }

}