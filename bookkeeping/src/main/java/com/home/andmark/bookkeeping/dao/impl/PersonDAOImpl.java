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
public class PersonDAOImpl implements PersonDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Person read(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> readAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p",
                Person.class).getResultList();
    }

    @Override
    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class,id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setPatronymic(updatedPerson.getPatronymic());
        personToBeUpdated.setSurname(updatedPerson.getSurname());
        personToBeUpdated.setBirthday(updatedPerson.getBirthday());
        personToBeUpdated.setBooks(updatedPerson.getBooks());
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));
    }

}
