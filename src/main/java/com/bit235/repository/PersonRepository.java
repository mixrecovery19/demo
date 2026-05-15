package com.bit235.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit235.model.Person;

public interface PersonRepository
        extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);
}