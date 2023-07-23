package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.config.SpringConfig;
import com.home.andmark.bookkeeping.dao.impl.JdbcPersonDAOImpl;
import com.home.andmark.bookkeeping.dto.PersonDTO;
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
    private PersonDTO personDTO;
    private Person person;

    @Mock
    private ModelMapper mapper;

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
        person.setId(5);
        person.setName("Tati");
        person.setPatronymic("Mich");
        person.setSurname("Koz");
        person.setBirthday(1988);

        when(personDAO.read(anyInt())).thenReturn(person);
        PersonDTO actual = personService.read(5);
        assertEquals(personDTO, actual);
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