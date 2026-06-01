package com.bit235.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit235.model.Person;
import com.bit235.service.PersonService;

// arguably this is the main controller class for the Person entity and handles Person-related actions
@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(
            PersonService personService
    ) {
        this.personService =
                personService;
    }

    // handles the http GET request for the create user accont for, literallly passing an empty Person object in prepartion for the form to be filled out and submitted
    @GetMapping("/create-user-account")
    public String showCreateUserForm(
            Model model
    ) {

        model.addAttribute(
                "person",
                new Person()
        );

        return "createForm";
    }

    // method to handle a user account creation form's submission. Simply takes the form data, as a first step in the signup/save flow.
    @PostMapping("/create-user-account/save")
    public String saveUser(
            Person person
    ) {

        // normal user only
        person.setAdmin(false);

        personService.savePerson(
                person
        );

        return "redirect:/login";
    }
    
}