package com.home.andmark.bookkeepingsb.controller;

import com.home.andmark.bookkeepingsb.dto.PersonDTO;
import com.home.andmark.bookkeepingsb.service.PersonService;
import com.home.andmark.bookkeepingsb.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping({"/index.html", "/index"})
    public String showAll(Model model) {
        model.addAttribute("persons", personService.findAllDao());
        return "persons/index";
    }

    @GetMapping("/{id}")
    public String showPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findOne(id));
        model.addAttribute("books", personService.getBooksByPersonId(id));
        return "persons/show";
    }

    @GetMapping({"/new", "new.html"})
    public String newPerson(@ModelAttribute("person") PersonDTO personDTO) {
        return "persons/new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("person") @Valid PersonDTO personDTO,
                         BindingResult bindingResult) {

        personValidator.validate(personDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return "persons/new";
        }

        personService.save(personDTO);
        return "redirect:persons/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.findOne(id));
        return "persons/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid PersonDTO personDTO, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "persons/edit";

        personService.update(id, personDTO);
        return "redirect:/persons/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/persons/index";
    }
}
