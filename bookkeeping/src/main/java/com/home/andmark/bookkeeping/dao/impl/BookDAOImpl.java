package com.home.andmark.bookkeeping.dao.impl;

import com.home.andmark.bookkeeping.dao.BookDAO;
import com.home.andmark.bookkeeping.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookDAOImpl implements BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book read(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> readAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b",
                Book.class).getResultList();
    }

    @Override
    @Transactional
    public void update(int id, Book updatedBook) {
        Session session = sessionFactory.getCurrentSession();
        Book bookToBeUpdated = session.get(Book.class, id);

        bookToBeUpdated.setTitle(updatedBook.getTitle());
        bookToBeUpdated.setAuthor(updatedBook.getAuthor());
        bookToBeUpdated.setYear(updatedBook.getYear());
        bookToBeUpdated.setOwner(updatedBook.getOwner());
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class, id));
    }

}
