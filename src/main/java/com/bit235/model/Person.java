package com.bit235.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import java.util.List;

@Entity// JPA annotation to mark this class as a database entity
public class Person {
// model class for Person. Gets mildly complex in the sense that we needed to differentiate between users and admins
// which is where isAdmin comes in. Simply creating a boolean attribute that tells Spring literally isAdmin = true or false allowing for me
// to then do things based on the isAdmin = true or false values... particularly relevant for the session handling, also involving the differentiation of a User and Admin at the login/BCrypt stage. 
// Also involving Thymeleaf conditional rendering of admin features in the HTML pages, among others. 
// This is a great example of how we can add attributes to our model classes and then use those attributes in various ways across the project.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String familyname;
    private String givenname;
    private Boolean isAdmin = false;
    private LocalDate dob;

    @Column(columnDefinition = "TEXT")
    private String biography;

    /*@ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;*/

    public Person() {
    }
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Article> articles;
    // 🔹 ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // 🔹 Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    // 🔹 Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    // 🔹 Family Name
    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname)
    {
        this.familyname = familyname;
    }

    // 🔹 Given Name
    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname)
    {
        this.givenname = givenname;
    }

    // 🔹 Date of Birth
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob)
    {
        this.dob = dob;
    }

    // 🔹 Biography
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography)
    {
        this.biography = biography;
    }
    // 🔹 Country
    /*public Country getCountry() {
        return country;
    }
    public void setCountry(Country country)
    {
        this.country = country;
    }*/
    public List<Article> getArticles() {
    return articles;
    }

    public void setArticles(List<Article> articles)
    {
        this.articles = articles;
    }

    // establishes an admin role for the person entity allowing us to differentiate between users and admins in the application
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public Boolean isAdmin() {
    return isAdmin;
    }
    // The setter for isAdmin allows us to assign admin privileges to a user
    public void setAdmin(Boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    //separates the user from the admin role allowing me to create a user/admin type of flow to the project
    public Boolean isUser() {
        return !isAdmin;
    }
}