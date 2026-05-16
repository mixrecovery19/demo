package com.bit235.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Person {

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

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Person() {
    }

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

    public void setUsername(
            String username
    ) {
        this.username = username;
    }

    // 🔹 Password
    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password
    ) {
        this.password = password;
    }

    // 🔹 Family Name
    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(
            String familyname
    ) {
        this.familyname = familyname;
    }

    // 🔹 Given Name
    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(
            String givenname
    ) {
        this.givenname = givenname;
    }

    // 🔹 Date of Birth
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(
            LocalDate dob
    ) {
        this.dob = dob;
    }

    // 🔹 Biography
    public String getBiography() {
        return biography;
    }

    public void setBiography(
            String biography
    ) {
        this.biography = biography;
    }

    // 🔹 Country
    public Country getCountry() {
        return country;
    }

    public void setCountry(
            Country country
    ) {
        this.country = country;
    }

    // 🔹 Admin Role
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(
            Boolean isAdmin
    ) {
        this.isAdmin = isAdmin;
    }

    // 🔹 Convenience Method
    public Boolean isUser() {
        return !isAdmin;
    }
}