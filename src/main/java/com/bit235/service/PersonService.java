package com.bit235.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;
import java.util.List;

import com.bit235.model.Person;
import com.bit235.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(
            PersonRepository personRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.personRepository =
                personRepository;
        this.passwordEncoder =
                passwordEncoder;
    }

  // 🔹 Login
public Person login(
        String username,
        String password
) {

    // TEMP: generate BCrypt hash for "1"
    System.out.println(
            "TEMP HASH FOR 1: "
            + passwordEncoder.encode("1")
    );

    debugUsers();

    System.out.println(
            "========== LOGIN ATTEMPT =========="
    );

    System.out.println(
            "USERNAME ENTERED: "
            + username
    );

    System.out.println(
            "PASSWORD ENTERED: "
            + password
    );

    Person person =
            personRepository
                    .findByUsername(
                            username
                    )
                    .orElse(null);

    if (person == null) {

        System.out.println(
                "USER NOT FOUND"
        );

        return null;
    }

    System.out.println(
            "FOUND USER: "
            + person.getUsername()
    );

    System.out.println(
            "DATABASE HASH: "
            + person.getPassword()
    );

    boolean matches =
            passwordEncoder.matches(
                    password,
                    person.getPassword()
            );

    System.out.println(
            "PASSWORD MATCHES: "
            + matches
    );

    System.out.println(
            "==================================="
    );

    if (matches) {
        return person;
    }

    return null;
}

    // 🔹 Save New User
    public void savePerson(
            Person person
    ) {

        // hash password
        person.setPassword(
                passwordEncoder.encode(
                        person.getPassword()
                )
        );

        // save user
        personRepository.save(
                person
        );
    }

        public Person getPersonById(@NonNull Long id) {
        return personRepository
                .findById(id)
                .orElse(null);
    }
                public void debugUsers() {

                System.out.println(
                        "===== USERS IN DATABASE ====="
                );

                personRepository
                        .findAll()
                        .forEach(person ->

                                System.out.println(
                                        "USERNAME: "
                                        + person.getUsername()
                                )
                        );

                System.out.println(
                        "============================="
                );
                }

                public List<Person> findAll() {
                return personRepository.findAll();
                }

                public Person findById(@NonNull Long id) {
                return personRepository.findById(id)
                        .orElse(null);
                }

                public void deleteById(@NonNull Long id) {
                personRepository.deleteById(id);
                }

                public void saveExistingUser(
        Person updatedPerson
) {

                Long id = updatedPerson.getId();

                if (id == null) {
                        return;
                }

    Person existingPerson =
            personRepository
                    .findById(
                                                                id
                    )
                    .orElse(null);

    if (existingPerson != null) {

        // prevent password being lost
        if (
            updatedPerson.getPassword()
            == null
            || updatedPerson
                    .getPassword()
                    .isBlank()
        ) {

            updatedPerson.setPassword(
                    existingPerson
                            .getPassword()
            );
        }

        personRepository.save(
                updatedPerson
        );
    }
}
        }
