package com.bit235.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit235.model.Article;
//JpaRepository is a Spring Data interface that provides the CRUD operations for the, in this case, the Article entiry.
// By extending JpaRepository, ArticleRepository inherits methods for saving, deleting, and finding Article entities without needing to write any implementation code.
// Which is one of the core prinicples of OOL using INHERITANCE to allow for passing from Parent to Child classes, albeit, in this example it is 
// performing a INTERFACE INHERITANCE however the prinicple is still the same int eh sense that we are passing from Parent to Child.
//In this case it was done for me by Spring Boot, I just needed to create the interface, then extend the repository(JpaRepository).
public interface ArticleRepository extends JpaRepository<Article, Long> {
}