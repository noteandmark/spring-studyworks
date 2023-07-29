package com.home.andmark.bookkeeping.dao;

import com.home.andmark.bookkeeping.model.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<Person> readAllPersons() {
        Session session = entityManager.unwrap(Session.class);

        List<Person> persons = session.createQuery("select distinct p from Person p " +
                        "LEFT JOIN FETCH p.books")
                .getResultList();

        System.out.println(persons);
        return persons;
    }
}
