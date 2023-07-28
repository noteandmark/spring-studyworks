package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.dao.impl.JdbcBookDAOImpl;
import com.home.andmark.bookkeeping.dto.BookDTO;
import com.home.andmark.bookkeeping.model.Book;
import com.home.andmark.bookkeeping.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final JdbcBookDAOImpl bookDAO;
    private final ModelMapper mapper;

    @Autowired
    public BookServiceImpl(JdbcBookDAOImpl bookDAO, ModelMapper mapper) {
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
        List<Book> books = bookDAO.readAll();
        return mapListOfEntityToDTO(books);
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
//
//        Book book = bookDAO.read(bookId);
//
//        book.setPersonId(personId);
//        bookDAO.update(bookId, book);
    }

    public void releaseBook(int id) {
//        BookDTO book = read(id);
//        if (book != null) {
//            book.setPersonId(null);
//            update(id, book);
//        }
    }
}
