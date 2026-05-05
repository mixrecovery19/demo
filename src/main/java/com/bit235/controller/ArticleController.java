package com.bit235.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit235.model.Article;
import com.bit235.model.User;
import com.bit235.service.ArticleService;

//article controller for article methods.
@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // method to show articles.
   @GetMapping("/articles")
        public String showArticles(Model model, HttpSession session) {

            User user = (User) session.getAttribute("user");// get stored user info so we know who wrote the article and can display it on their personalised article list. 

            if (user == null) {
                return "redirect:/login";   // ✅ prevents crash
            }

            model.addAttribute("user", user);
            model.addAttribute("articles", articleService.getAllArticles());

            return "articleList"; // not properly wired yet, this will eventually look for articles and display them from the json file or database.
        }

    // method to show article form and save the article.
    @GetMapping("/articles/new")
    public String showArticleForm(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "articleForm"; //this will look for and find, hopefully, articleForm.html in the templates folder and render it for us.
    }
    //method to save the article.
    @PostMapping("/articles/save")
    public String saveArticle(Article article) {

        System.out.println("TITLE: " + article.getTitle());
        System.out.println("CONTENT: " + article.getContent());

        articleService.saveArticle(article);

        return "redirect:/articles"; // after saving, we want to go back to the article list page to see our new article in the list.
    }
}