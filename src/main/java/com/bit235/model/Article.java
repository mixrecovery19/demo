package com.bit235.model;

import jakarta.persistence.*;
//model class for Article. Helps define the Article table design, sets and gets attributes, and manages entity cardinality in Spring Boot that is through what are
// commonly known as GETTERS and SETTERS. You could think of it as the ERD of the Spring project.

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
// Getters and Setters for Article class, these provide a great place to simply add logic to our classes.
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public String getIdDisplay() {
        return "#" + id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    public Category getCategory() {
    return category;
    }
    // setter for Category... we could add elements and attributes to our Category class quite easily once
    // setters and getteers are in place.
    public void setCategory(Category category) {
        this.category = category;
    }
}