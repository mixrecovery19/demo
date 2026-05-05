package com.bit235.service;
import com.bit235.model.Article;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String FILE_PATH = "articles.json";

    // 🔹 Read all articles
    public List<Article> getAllArticles() {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                return new ArrayList<>();
            }

            return objectMapper.readValue(file, new TypeReference<List<Article>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // 🔹 Save a new article
    public void saveArticle(Article article) {
        try {
            List<Article> articles = getAllArticles();
            articles.add(article);

            objectMapper.writeValue(new File(FILE_PATH), articles);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}