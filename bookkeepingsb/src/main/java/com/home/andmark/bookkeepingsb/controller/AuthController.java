package com.home.andmark.bookkeepingsb.controller;

import com.home.andmark.bookkeepingsb.dto.PersonDTO;
import com.home.andmark.bookkeepingsb.model.Person;
import com.home.andmark.bookkeepingsb.security.JWTUtil;
import com.home.andmark.bookkeepingsb.service.impl.RegistrationService;
import com.home.andmark.bookkeepingsb.util.PersonValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper mapper;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService, JWTUtil jwtUtil, ModelMapper mapper) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") PersonDTO personDTO) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public Map<String,String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                      BindingResult bindingResult) {
        personValidator.validate(personDTO, bindingResult);
        if (bindingResult.hasErrors())
            return Map.of("message","Error perform registration!");

        registrationService.register(personDTO);

        String token = jwtUtil.generateToken(personDTO.getSurname());
        return Map.of("jwt-token",token);
    }

    public Person convertToPerson(PersonDTO personDTO) {
        return this.mapper.map(personDTO, Person.class);
    }
}
