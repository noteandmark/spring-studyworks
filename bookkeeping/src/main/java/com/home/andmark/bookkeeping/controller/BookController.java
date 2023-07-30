package com.home.andmark.bookkeeping.controller;

import com.home.andmark.bookkeeping.dto.BookDTO;
import com.home.andmark.bookkeeping.dto.PersonDTO;
import com.home.andmark.bookkeeping.service.BookService;
import com.home.andmark.bookkeeping.service.PersonService;
import com.home.andmark.bookkeeping.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookService bookService, PersonService personService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.personService = personService;
        this.bookValidator = bookValidator;
    }

    @GetMapping({"/index.html","/index",""})
    public String showAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(name = "books_per_page", required = false, defaultValue = "0") int booksPerPage,
                        Model model) {
        List<BookDTO> books;
        int totalBooks = bookService.countAllBooks();
        int totalPages;

        if (booksPerPage > 0) {
            // If books_per_page is specified, use pagination
            totalPages = (int) Math.ceil((double) totalBooks / booksPerPage);
            books = bookService.findAllPaginated(page, booksPerPage);
        } else {
            // If books_per_page is not specified, get all books
            totalPages = 0;
            books = bookService.findAll();
        }

        // Determine if there is a next page
        boolean hasNextPage = page < totalPages - 1;

        model.addAttribute("books", books);
        model.addAttribute("hasNextPage", hasNextPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("selectedPerPage", booksPerPage);

        return "templates/books/index";
    }

    @GetMapping("/{id}")
    public String showBookById(@PathVariable("id") int id, Model model) {
        BookDTO book = bookService.findOne(id);
        PersonDTO assignedPerson = new PersonDTO();
        if (book == null) {
            // Handle book not found, redirect to an error page or show an error message
            return "redirect:/error";
        }
        // Get the list of all persons
        List<PersonDTO> persons = personService.findAll();
        if (book.getOwner() != null) {
            // Find the person with the corresponding personId
            assignedPerson = persons.stream()
                    .filter(person -> person.getId() == book.getOwner().getId())
                    .findFirst()
                    .orElse(null);
        }

        model.addAttribute("book", book);
        model.addAttribute("persons", persons);
        model.addAttribute("assignedPerson", assignedPerson);
        return "templates/books/show";
    }

    @GetMapping({"/new","new.html"})
    public String newBook(@ModelAttribute("book") BookDTO bookDTO) {
        return "templates/books/new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("book") @Valid BookDTO bookDTO,
                         BindingResult bindingResult) {

        bookValidator.validate(bookDTO, bindingResult);

        if (bindingResult.hasErrors())
            return "templates/books/new";

        bookService.save(bookDTO);
        return "redirect:books/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "templates/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid BookDTO bookDTO, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "templates/books/edit";

        bookService.update(id, bookDTO);
        return "redirect:/books/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books/index";
    }

    @PostMapping("/{id}/assign")
    public String assignBookToPerson(@PathVariable("id") int bookId, @RequestParam("person") int personId) {
        bookService.assignBookToPerson(bookId, personId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        bookService.releaseBook(id);
        return "redirect:/books/" + id;
    }
}