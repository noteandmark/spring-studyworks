package com.home.andmark.bookkeeping.repository;

import com.home.andmark.bookkeeping.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepository extends JpaRepository<Person, Integer> {
}
