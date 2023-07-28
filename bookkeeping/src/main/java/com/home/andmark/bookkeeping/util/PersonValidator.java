package com.home.andmark.bookkeeping.util;

import com.home.andmark.bookkeeping.dto.PersonDTO;
import com.home.andmark.bookkeeping.service.PersonService;
import com.home.andmark.bookkeeping.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonServiceImpl personService;

    @Autowired
    public PersonValidator(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PersonDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDTO personDTO = (PersonDTO) o;

        // Check that a person's names starts with a capital letter
        if (!Character.isUpperCase(personDTO.getName().codePointAt(0)))
            errors.rejectValue("name", "", "Name should start with a capital letter");
        if (!Character.isUpperCase(personDTO.getName().codePointAt(0)))
            errors.rejectValue("patronymic", "", "Patronymic should start with a capital letter");
        if (!Character.isUpperCase(personDTO.getName().codePointAt(0)))
            errors.rejectValue("surname", "", "Surname should start with a capital letter");
    }
}
