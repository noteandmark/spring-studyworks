package com.home.andmark.bookkeeping.dao.impl;

import com.home.andmark.bookkeeping.dao.PersonDAO;
import com.home.andmark.bookkeeping.model.Person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class JdbcPersonDAOImpl implements PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public JdbcPersonDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Person person) {

    }

    @Override
    public Person read(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<Person> readAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> people = session.createQuery("select p from person p",
                Person.class).getResultList();
        return people;
    }

    @Override
    public void update(int id, Person updatedPerson) {

    }


    @Override
    public void delete(int id) {
    }

}
