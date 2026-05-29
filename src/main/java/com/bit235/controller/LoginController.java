package com.bit235.controller;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.core.io.Resource;
import java.util.Arrays;
import java.util.List;

import com.bit235.model.Article;
import com.bit235.model.Person;
import com.bit235.service.PersonService;
import com.bit235.service.ArticleService;

@Controller
public class LoginController {

    private final PersonService personService;
    private final ArticleService articleService;

    public LoginController(
            PersonService personService,
            ArticleService articleService
    ) {
        this.personService =
                personService;
        this.articleService =
                articleService;
    }

    // 🔹 Home Page
   @GetMapping("/")
        public String home(
                Model model,
                HttpSession session
        ) throws IOException {

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

        // 🔹 Load all images
        Resource[] files =
                new PathMatchingResourcePatternResolver()
                        .getResources(
                                "classpath:/static/images/*"
                        );

        List<String> images =
                Arrays.stream(files)
                        .map(file ->
                                "/images/"
                                + file.getFilename()
                        )
                        .toList();

        model.addAttribute(
                "images",
                images
        );
        Article featuredArticle =
        articleService
                .getRandomArticle();

        model.addAttribute(
                "featuredArticle",
                featuredArticle
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