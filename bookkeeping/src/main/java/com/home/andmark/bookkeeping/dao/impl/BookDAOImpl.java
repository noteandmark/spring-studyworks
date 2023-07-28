package com.home.andmark.bookkeeping.dao.impl;

import com.home.andmark.bookkeeping.dao.BookDAO;
import com.home.andmark.bookkeeping.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAOImpl implements BookDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public Book read(int id) {
        return null;
    }

    @Override
    public List<Book> readAll() {
        return null;
    }

    @Override
    public void update(int id, Book updatedBook) {

    }

    @Override
    public void delete(int id) {
    }

}
