package com.bit235.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    // 🔹 NEW ARTICLE
    if (article.getId() == null) {

        article.setAuthor(user);

    }

    // 🔹 EXISTING ARTICLE
    else {

        Article existingArticle =
                articleService
                        .getArticleById(
                                article.getId()
                        );

        if (existingArticle == null) {
            return "redirect:/articles";
        }

        // Keep original author
        article.setAuthor(
                existingArticle.getAuthor()
        );
    }

    articleService.saveArticle(
            article
    );

    return "redirect:/articles";
}

        // 🔹 Edit article form (LOGIN REQUIRED)
        @GetMapping("/articles/edit/{id}")
        public String editArticle(
                @PathVariable Long id,
                Model model,
                HttpSession session
        ) {

        Person user =
                (Person) session
                        .getAttribute("user");

        if (user == null) {
                return "redirect:/login";
        }

       Article article =
        articleService
                .getArticleById(id);
                if (article == null) {
                return "redirect:/articles";
                }

    // Permission check
    boolean canEdit =
            user.getIsAdmin()
            ||
            article.getAuthor()
                   .getId()
                   .equals(user.getId());

    if (!canEdit) {
        return "redirect:/articles";
    }

    model.addAttribute(
            "user",
            user
    );

    model.addAttribute(
            "article",
            article
    );

    model.addAttribute(
            "categories",
            categoryService
                    .getAllCategories()
    );

    return "articleForm";
}
@GetMapping("/articles/delete/{id}")
public String deleteArticle(
        @PathVariable Long id,
        HttpSession session
) {

    Person user =
            (Person) session
                    .getAttribute("user");

    if (user == null) {
        return "redirect:/login";
    }

    Article article =
            articleService
                    .getArticleById(id);

    // Permission check
    if (article == null) {
        return "redirect:/articles";
    }

    boolean canDelete =
            user.getIsAdmin()
            ||
            article.getAuthor()
                   .getId()
                   .equals(user.getId());

    if (!canDelete) {
        return "redirect:/articles";
    }

    try {

        articleService
                .deleteArticle(id);

    } catch (Exception e) {

        throw new IllegalStateException(
                "Unable to delete article",
                e
        );
    }

    return "redirect:/articles";
}
}