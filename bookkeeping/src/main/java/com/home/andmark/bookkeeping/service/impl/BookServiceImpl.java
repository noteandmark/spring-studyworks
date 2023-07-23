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
    public BookDTO save(BookDTO bookDTO) {
        Book book = mapper.map(bookDTO, Book.class);
        return mapper.map(bookDAO.save(book), BookDTO.class);
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
    public BookDTO update(int id, BookDTO bookDTO) {
        Book updated = bookDAO.update(id, mapper.map(bookDTO, Book.class));
        return mapper.map(updated, BookDTO.class);
    }

    @Override
    public int delete(int id) {
        return bookDAO.delete(id);
    }

    private List<BookDTO> mapListOfEntityToDTO(List<Book> all) {
        return all.stream().map(book -> mapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }
}
