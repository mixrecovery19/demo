package com.bit235.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit235.model.Article;
import com.bit235.model.Person;
import com.bit235.service.ArticleService;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(
            ArticleService articleService
    ) {
        this.articleService = articleService;
    }

    // 🔹 Show all articles
    @GetMapping("/articles")
    public String showArticles(
            Model model,
            HttpSession session
    ) {

        Person user =
                (Person) session
                        .getAttribute("user");

        // must be logged in
        if (user == null) {
            return "redirect:/login";
        }

        // DEBUG
        System.out.println(
            "LOGGED USER: "
            + user.getUsername()
        );

        System.out.println(
            "ARTICLES FOUND: "
            + articleService
                    .getAllArticles()
                    .size()
        );

        model.addAttribute(
                "user",
                user
        );

        model.addAttribute(
                "articles",
                articleService.getAllArticles()
        );

        return "articleList";
    }

    // 🔹 Show create form
    @GetMapping("/articles/new")
    public String showArticleForm(
            Model model,
            HttpSession session
    ) {

        Person user =
                (Person) session
                        .getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "user",
                user
        );

        model.addAttribute(
                "article",
                new Article()
        );

        return "articleForm";
    }

    // 🔹 Save article
    @PostMapping("/articles/save")
    public String saveArticle(
            Article article,
            HttpSession session
    ) {

        Person user =
                (Person) session
                        .getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        // attach article to logged in user
        article.setAuthor(user);

        articleService.saveArticle(
                article
        );

        return "redirect:/articles";
    }
}