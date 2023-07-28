package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.dao.BookDAO;
import com.home.andmark.bookkeeping.dto.BookDTO;
import com.home.andmark.bookkeeping.dto.PersonDTO;
import com.home.andmark.bookkeeping.model.Book;
import com.home.andmark.bookkeeping.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    private final BookDAO bookDAO;
    private final ModelMapper mapper;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO, ModelMapper mapper) {
        this.bookDAO = bookDAO;
        this.mapper = mapper;
    }

    @Override
    public void save(BookDTO bookDTO) {
        Book book = mapper.map(bookDTO, Book.class);
        bookDAO.save(book);
    }

    @Override
    public BookDTO read(int id) {
        Book book = bookDAO.read(id);
        BookDTO bookDTO = mapper.map(book, BookDTO.class);
        return bookDTO;
    }

    @Override
    public List<BookDTO> readAll() {
        return mapListOfEntityToDTO(bookDAO.readAll());
    }

    @Override
    public void update(int id, BookDTO bookDTO) {
        bookDAO.update(id, mapper.map(bookDTO, Book.class));
    }

    @Override
    public void delete(int id) {
        bookDAO.delete(id);
    }

    private List<BookDTO> mapListOfEntityToDTO(List<Book> all) {
        return all.stream().map(book -> mapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    public void assignBookToPerson(int bookId, int personId) {
//        BookDTO bookDTO = read(bookId);
//        if (bookDTO != null) {
//            PersonDTO personDTO = read(personId);
//            if (personDTO != null) {
//                bookDTO.setOwner(personDTO);
//                update(bookId, bookDTO);
//            } else {
//                // Handle the case when the person with the given ID is not found
//                // You can throw an exception or handle it based on your requirements
//            }
//        } else {
//            // Handle the case when the book with the given ID is not found
//            // You can throw an exception or handle it based on your requirements
//        }
//        Book book = bookDAO.read(bookId);
//
//        book.setPersonId(personId);
//        bookDAO.update(bookId, book);
    }

    private PersonDTO readPerson(int personId) {
        // Implement the logic to read a person by their ID using the PersonDAO or PersonService
        // and return the corresponding PersonDTO
        return null;
    }

    public void releaseBook(int id) {
        BookDTO bookDTO = read(id);
        if (bookDTO != null) {
            bookDTO.setOwner(null);
            update(id, bookDTO);
        }
    }
}
