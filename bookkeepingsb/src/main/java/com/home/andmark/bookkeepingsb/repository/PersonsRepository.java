package com.home.andmark.bookkeepingsb.repository;

import com.home.andmark.bookkeepingsb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository extends JpaRepository<Person, Integer> {

    List<Person> findBySurname(String surname);

    List<Person> findBySurnameStartingWith(String startingWith);

    List<Person> findByNameOrSurname(String name, String surname);
}
