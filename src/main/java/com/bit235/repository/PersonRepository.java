package com.bit235.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bit235.model.Person;

public interface PersonRepository
        extends JpaRepository<Person, Long> {
// OK, great, so this example shows not only the use of JpaRepository, but some Java/Spring "findby magic" to use our tutors words
// In effect that magic is including an inbuilt parser, which is, in fact built into Spring DATA Jpa, and is 
// in effect, in this case simply a SELECT * FROM person WHERE username = ? query, but we can just write it as findByUsername and Spring Boot will do the rest for us.
// Likewise another important pont to make is the user of Optional.
// Optional is a container object which may or may not contain a non-null value.
// In short, Optional is about "Null Safety". We, as developers, could actually achieve this ourselves by wrting thoroug code that checks for null
// values before performing whatever it is that we want it to do, in this case, search for a user by username.
// Using Optional helps reduce the risk of what is technically called a NullPointerException. 
// Technically, we could still get a personOptional.get() without checking if isPresent() or without orElseThrow() catch block which would lead to a NullPointerException,
// Optional reduces this.
    Optional<Person> findByUsername(String username);
}