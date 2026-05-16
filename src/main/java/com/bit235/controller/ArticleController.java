package com.bit235.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit235.model.Article;
import com.bit235.model.Person;
import com.bit235.service.ArticleService;
import com.bit235.service.CategoryService;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    public ArticleController(
            ArticleService articleService,
            CategoryService categoryService
    ) {
        this.articleService =
                articleService;
        this.categoryService =
                categoryService;
    }

    // 🔹 Public article list
    @GetMapping("/articles")
    public String showArticles(
            Model model,
            HttpSession session
    ) {

        Person user =
                (Person) session
                        .getAttribute("user");

        // DEBUG
        System.out.println(
            "LOGGED USER: "
            + (user != null
               ? user.getUsername()
               : "Guest")
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
                articleService
                        .getAllArticles()
        );

        return "articleList";
    }

    // 🔹 Create form (LOGIN REQUIRED)
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
       model.addAttribute(

            "categories",

            categoryService.getAllCategories()

    );

        return "articleForm";
    }

    // 🔹 Save article (LOGIN REQUIRED)
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

        // attach logged in user
        article.setAuthor(user);

        articleService.saveArticle(
                article
        );

        return "redirect:/articles";
    }
}