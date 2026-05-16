package com.bit235.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit235.model.Person;
import com.bit235.service.PersonService;

@Controller
public class LoginController {

    private final PersonService personService;

    public LoginController(
            PersonService personService
    ) {
        this.personService =
                personService;
    }

    // 🔹 Home Page
    @GetMapping("/")
    public String home(
            Model model,
            HttpSession session
    ) {

        Person user =
                (Person) session
                        .getAttribute(
                                "user"
                        );

        model.addAttribute(
                "user",
                user
        );

        model.addAttribute(
                "isAdmin",
                user != null
                && Boolean.TRUE.equals(
                        user.getIsAdmin()
                )
        );

        return "index";
    }

    // 🔹 Login Page
    @GetMapping("/login")
    public String showLogin(
            Model model
    ) {

        model.addAttribute(
                "person",
                new Person()
        );

        return "login";
    }

    // 🔹 Login Action
    @PostMapping("/login")
    public String handleLogin(
            Person formUser,
            Model model,
            HttpSession session
    ) {

        Person loggedInUser =
                personService.login(
                        formUser.getUsername(),
                        formUser.getPassword()
                );

        if (loggedInUser != null) {

            session.setAttribute(
                    "user",
                    loggedInUser
            );

            session.setAttribute(
                    "isAdmin",
                    loggedInUser.getIsAdmin()
            );

            return "redirect:/";
        }

        model.addAttribute(
                "error",
                "Invalid username or password"
        );

        return "login";
    }

    // 🔹 Logout
    @GetMapping("/logout")
    public String logout(
            HttpSession session
    ) {

        session.invalidate();

        return "redirect:/";
    }
}