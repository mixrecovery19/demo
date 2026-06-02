package com.bit235.service;

import java.util.List;
import java.util.Random;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.bit235.model.Article;
import com.bit235.repository.ArticleRepository;

@Service
public class ArticleService {
// Service class for Article. Handles the business logic for Article, and is a smaller class that is easier to read and maintain.
// "Auto-Fixed" the warnings of the Null Safety and was introduced to @NonNull annotatiosn which are obviously
// Spring Boots "magic" way of ensuring important attributes are protected against null values leading to NullPointerExceptions.
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void saveArticle(@NonNull Article article) {
        articleRepository.save(article);
    }
    public void deleteArticle(@NonNull Long id)/*Null Safety */
    {
        articleRepository 
            .deleteById(id);
    }
    public Article getArticleById(@NonNull Long id)/*Null Safety */
    {
        return articleRepository
                .findById(id)
                .orElse(null);
    }
    // this method is the start of the randomArticle gerneation.
    public Article getRandomArticle() {

        List<Article> articles =
                articleRepository
                        .findAll();

        if (articles.isEmpty()) {
            return null;
        }
// this is a Random Article gernation method, simply attaches a Random object(in built Java class) to the list of articles, to the ArticleRepository
// allowing me to use it where I like. In this case in the HomeController which then passes through to the home.html page allowin
// Thymeleaf to render a random article and display it on the home page as a neat little "Feature Article".
        Random random = new Random();

        return articles.get(
                random.nextInt(articles.size())                
        );
    }
}