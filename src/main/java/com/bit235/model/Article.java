package com.bit235.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Model class for Article.
// Helps define the Article table design,
// entity relationships, validation rules,
// getters and setters.
// Think of it similarly to the ERD
// inside the Spring Boot project.
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title validation:
    // - cannot be blank
    // - prevents excessive input
    @NotBlank(message = "Title is required")
    @Size(max = 150,
            message = "Title cannot exceed 150 characters")
    private String title;

    // Content validation:
    // - cannot be blank
    // - prevents excessive input
    @NotBlank(message = "Content is required")
    @Size(max = 5000,
            message = "Content cannot exceed 5000 characters")
    @Column(columnDefinition = "TEXT")
    private String content;

    // Many Articles belong to one Person
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    // Many Articles belong to one Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Article() {
    }

    // 🔹 ID
    public Long getId() {
        return id;
    }

    public String getIdDisplay() {
        return "#" + id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // 🔹 Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // 🔹 Content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // 🔹 Author
    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    // 🔹 Category
    public Category getCategory() {
        return category;
    }

    // Setter for Category
    public void setCategory(Category category) {
        this.category = category;
    }
}