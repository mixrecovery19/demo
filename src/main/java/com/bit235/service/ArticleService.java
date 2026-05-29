package com.bit235.service;

import java.util.List;
import java.util.Random;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.bit235.model.Article;
import com.bit235.repository.ArticleRepository;

@Service
public class ArticleService {

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
    public void deleteArticle(
        @NonNull Long id/*Null Safety */
    ) {
        articleRepository
                .deleteById(id);
    }
    public Article getArticleById(
            @NonNull Long id
    ) {
        return articleRepository
                .findById(id)
                .orElse(null);
    }
    public Article getRandomArticle() {

    List<Article> articles =
            articleRepository
                    .findAll();

    if (articles.isEmpty()) {
        return null;
    }

    Random random =
            new Random();

    return articles.get(
            random.nextInt(
                    articles.size()
            )
    );
}
}