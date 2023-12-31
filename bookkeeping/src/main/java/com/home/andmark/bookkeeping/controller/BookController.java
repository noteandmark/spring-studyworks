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
                        @RequestParam(value = "sort_by_year", required = false) Boolean sortByYear,
                        Model model) {
        List<BookDTO> books = null;

        if (Boolean.TRUE.equals(sortByYear)) {
            // If sort_by_year is true, use sorting by year
            books = bookService.findAllSortedByYear();
        } else {
            books = bookService.findAll();
        }

        if (booksPerPage > 0) {
            // If books_per_page is specified, use pagination
            int totalBooks = bookService.countAllBooks();
            int totalPages = (int) Math.ceil((double) totalBooks / booksPerPage);
            // Determine if there is a next page
            boolean hasNextPage = page < totalPages - 1;
            books = bookService.findAllPaginated(page, booksPerPage);
            model.addAttribute("hasNextPage", hasNextPage);
            model.addAttribute("totalPages",totalPages);
            model.addAttribute("selectedPerPage", booksPerPage);
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("books", books);

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

    @GetMapping({"/search.html","search"})
    public String searchPage() {
        return "templates/books/search";
    }

    @PostMapping({"/search.html","search"})
    public String searchBooks(@RequestParam("titleInitials") String titleInitials, Model model) {
        model.addAttribute("books", bookService.searchByTitle(titleInitials));
        return "templates/books/search";
    }
}