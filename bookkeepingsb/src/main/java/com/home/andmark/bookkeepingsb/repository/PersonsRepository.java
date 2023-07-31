package com.home.andmark.bookkeepingsb.repository;

import com.home.andmark.bookkeepingsb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findBySurname(String surname);

    List<Person> findBySurnameStartingWith(String startingWith);

    List<Person> findByNameOrSurname(String name, String surname);
}
