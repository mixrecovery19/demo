package com.bit235.model;

import jakarta.persistence.*;
//model class for Category. Handles the Category table design, entity relationships in Spring Boot and is a smaller
// less complext version of Articles allowing us to see a simpler model class.
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 1000)
    private String description;

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
// another example of a getter above, and this setter below. This is ineffect telling Spring Boot that Category needs an id, name, and description.
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // we could either add more attributes to Category, for example, with scale I would add an edit Category, Country, User etc. to the Admin Dashboard.
    }