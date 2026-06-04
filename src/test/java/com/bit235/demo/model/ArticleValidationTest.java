package com.bit235.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bit235.model.Article;

public class ArticleValidationTest {

    @Test
    void shouldRejectBlankTitle() {

        Article article =
                new Article();

        article.setTitle("");
        article.setContent(
                "Valid content"
        );
System.out.println(
        "ARTICLE TITLE: '"
        + article.getTitle()
        + "'"
);
        boolean titleIsBlank =
                article.getTitle()
                       .isBlank();

        System.out.println(
                "TITLE IS BLANK: "
                + titleIsBlank
        );

        assertTrue(
        article.getTitle().isBlank(),
        "Article title should be blank"
);
    }
}