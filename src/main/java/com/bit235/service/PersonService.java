package com.bit235.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;
import java.util.List;

import com.bit235.model.Person;
import com.bit235.repository.PersonRepository;

@Service
public class PersonService {
// Service class for Person. Handles business logic.
// Introduc the passwordEncoder to hash passwords and the personRepository to interact with the database.
    private final PersonRepository personRepository;
        private final PasswordEncoder passwordEncoder;
// now we use it inside the constructor. to "set the tone" of the PersonService class.
        public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
                this.personRepository = personRepository;
                this.passwordEncoder = passwordEncoder;
        }

        // Login method. Very straifht forward. username, password, except we introduce the passwordEncoder
        // and BCrypt hashing for security reasons.
        public Person login(String username, String password) {

        // TEMP: generate BCrypt hash for "1"
        // during the development and heuristic approach to the admin flow I added
        // a ExistomPerson save method which actually wrote over the data, to find it we added a TEMP HASH printout, 
        // which I have left in place for demonstration and possible furture use with demonstrations and usage on different
        // computers.
        System.out.println("TEMP HASH FOR 1: " + passwordEncoder.encode("1"));        

        debugUsers();

        System.out.println("========== LOGIN ATTEMPT ==========");
        System.out.println("USERNAME ENTERED: " + username);
        System.out.println("PASSWORD ENTERED: " + password);        

    Person person = personRepository
                    .findByUsername(username)
                    .orElse(null);

    if (person == null) {
// self explanatory, if person is null, then user was not found. Handle gracefully.
        System.out.println("USER NOT FOUND");        

        return null;
    }
//this returns the user that was found, again, allowing for a terminal visuall demonstration of what is going on for testing and for demonstration purposes.
    System.out.println("FOUND USER: " + person.getUsername());    
    System.out.println("DATABASE HASH: " + person.getPassword());

    boolean matches = passwordEncoder.matches(password, person.getPassword());            

    System.out.println("PASSWORD MATCHES: " + matches);  
    System.out.println("===================================");    

        if (matches) {
                return person;
        }

        return null;
        }

    // 🔹 Save New User
    public void savePerson(Person person) {
        // hash password
        person.setPassword(passwordEncoder.encode(person.getPassword()));        
        // save user
        personRepository.save(person);
        
    }
// this is a great little method to get the Person by ID and then
// return it. It allows for me to show how we use findBy and to display what we found in the terminal.
// it also helps with the testing and debugging.
        public Person getPersonById(@NonNull Long id) {
        return personRepository
                .findById(id)
                .orElse(null);
        }
                public void debugUsers() {

                System.out.println("===== USERS IN DATABASE =====");                

                personRepository
                        .findAll()
                        .forEach(person -> System.out.println("USERNAME: " + person.getUsername()));                             
                      
                System.out.println("=============================");
                
                }

                public List<Person> findAll() {
                return personRepository.findAll();
                }

                public Person findById(@NonNull Long id) {
                return personRepository.findById(id)
                        .orElse(null);
                }

               public void deleteById(@NonNull Long id)
                {

        System.out.println("========== SERVICE DELETE ==========");  
        System.out.println("LOOKING FOR USER: " + id);    

        Person person = personRepository
                        .findById(id)
                        .orElse(null);

        if (person == null) {

                System.out.println("USER NOT FOUND");        

                return;
        }

    System.out.println("FOUND USER: " + person.getUsername());    
    System.out.println("ARTICLE COUNT: " + (person.getArticles() != null ? person.getArticles().size() : 0));           

                try {

                        personRepository.delete(person);             
                        System.out.println("DELETE SUCCESSFUL");

                } catch (Exception e) {
                        System.out.println("DELETE FAILED");             
                        e.printStackTrace();
                }
        }

                public void saveExistingUser(Person updatedPerson){
                Long id = updatedPerson.getId();

                        if (id == null) {
                                return;
                        }

                Person existingPerson = personRepository
                                .findById(id)                    
                                .orElse(null);

                if (existingPerson != null) {

                        // prevent password being lost
                        if (
                        updatedPerson.getPassword()== null || updatedPerson .getPassword() .isBlank()) {
                        updatedPerson.setPassword(existingPerson .getPassword());
                        }
                        personRepository.save(updatedPerson);
                }
               
        }
        
        }
