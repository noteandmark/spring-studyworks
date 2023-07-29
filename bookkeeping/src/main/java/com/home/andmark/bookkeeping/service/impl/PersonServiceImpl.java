package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.dto.PersonDTO;
import com.home.andmark.bookkeeping.model.Person;
import com.home.andmark.bookkeeping.repository.PersonsRepository;
import com.home.andmark.bookkeeping.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonsRepository personsRepository;
    private final ModelMapper mapper;

    public PersonServiceImpl(PersonsRepository personsRepository, ModelMapper mapper) {
        this.personsRepository = personsRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void save(PersonDTO personDTO) {
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

//    private final PersonDAO personDAO;
//    private final BookDAO bookDAO;
//    private final ModelMapper mapper;
//
//    @Autowired
//    public PersonServiceImpl(PersonDAO personDAO, BookDAO bookDAO, ModelMapper mapper) {
//        this.personDAO = personDAO;
//        this.bookDAO = bookDAO;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public void save(PersonDTO personDTO) {
//        Person person = mapper.map(personDTO, Person.class);
//        personDAO.save(person);
//    }
//
//    @Override
//    public PersonDTO read(int id) {
//        Person person = personDAO.read(id);
//        PersonDTO personDTO = mapper.map(person, PersonDTO.class);
//        return personDTO;
//    }
//
//    @Override
//    public List<PersonDTO> readAll() {
//        return mapListOfEntityToDTO(personDAO.readAll());
//    }
//
//    @Override
//    public void update(int id, PersonDTO personDTO) {
//        personDAO.update(id, mapper.map(personDTO, Person.class));
//    }
//
//    @Override
//    public void delete(int id) {
//        personDAO.delete(id);
//    }
//
//    private List<PersonDTO> mapListOfEntityToDTO(List<Person> all) {
//        return all.stream().map(person -> mapper.map(person, PersonDTO.class))
//                .collect(Collectors.toList());
//    }
}
