package com.bit235.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Model class for Article. Helps define the Article table design, entity relationships, validation rules, getters and setters.
 A bit like the ERD of the database but in code form.*/
 
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title validation: -- cannot be blank and prevents excessive input.   
    // Spring Boot's Bean Validation annotations allows us to easliyt enforce validation rules in our model fields.
    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title cannot exceed 150 characters")
    private String title;
    /**
     * Content validation: again, cannot be blank and in this case prevents excessive input. We could not only
     * easily adjut the max size, we could also achieve this through JavaScript client side validation.
     */
    @NotBlank(message = "Content is required")
    @Size(max = 5000, message = "Content cannot exceed 5000 characters")
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
    public Long getId() {
        return id;
    }
    /**
     * Helper method to display ID with # prefix, technically is not necessary it is common pratices relying on Java string concatenation, 
     * where is calls toString.valueOf(id) behind the scenes to convert the Long to a String and then concatenates it with the #.
     * because if it did not do this there could possibly be a problem with the integer being treatd as a number not a string which would result in an eror
     * because it would read the # as a comment not part of the string. */
    
    public String getIdDisplay() {
        return "#" + id;
    }
    // rest of Article class getters and setters.
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
    public Category getCategory() {
        return category;
    }
    // Setter for Category
    public void setCategory(Category category) {
        this.category = category;
    }
}