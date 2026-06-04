package com.bit235.model;

import jakarta.persistence.*;
/*
 * Model class for Category.
 *
 * Handles:
 * - Category table structure
 * - Entity relationships
 * - Database mapping
 *
 * Categories are intentionally
 * simpler than Articles while
 * still demonstrating MVC,
 * OOP and database relationships.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;
    private String name;

    @Column(length = 1000)
    private String description;

    // Many Categories belong to one Person (author)
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;
    public Category() {
    }

    public Category(String name,String description)
    {
        this.name = name;
        this.description = description;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author)
    {
        this.author = author;
    }
}