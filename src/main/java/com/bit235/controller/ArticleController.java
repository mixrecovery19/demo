package com.bit235.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit235.model.Article;
import com.bit235.model.User;
import com.bit235.service.ArticleService;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 🔹 Article list page
   @GetMapping("/articles")
        public String showArticles(Model model, HttpSession session) {

            User user = (User) session.getAttribute("user");

            if (user == null) {
                return "redirect:/login";   // ✅ prevents crash
            }

            model.addAttribute("user", user);
            model.addAttribute("articles", articleService.getAllArticles());

            return "articleList";
        }

    // 🔹 Article form page
    @GetMapping("/articles/new")
    public String showArticleForm(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "articleForm";
    }
    @PostMapping("/articles/save")
    public String saveArticle(Article article) {

        System.out.println("TITLE: " + article.getTitle());
        System.out.println("CONTENT: " + article.getContent());

        articleService.saveArticle(article);

        return "redirect:/articles";
    }
}