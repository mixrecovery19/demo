package com.bit235.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit235.model.Person;
import com.bit235.service.PersonService;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(
            PersonService personService
    ) {
        this.personService =
                personService;
    }

    // 🔹 Show Create Account Page
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

    // 🔹 Save New User
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