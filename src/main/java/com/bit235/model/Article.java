package com.bit235.model;

import jakarta.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

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

public void setCategory(Category category) {
    this.category = category;
}
}