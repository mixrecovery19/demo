package com.bit235.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit235.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}