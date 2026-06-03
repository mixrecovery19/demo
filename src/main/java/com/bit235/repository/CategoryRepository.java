package com.bit235.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bit235.model.Category;
// JpaRepository is a Spring Data interface that provides the CRUD operations for the, in this case, the Category entity.
// Very much repeating what was said in the ArticleRepository.
// By using the JpaRepository interface, or technically, by extending it, we can then perform CRUD operations on the Category entity
// without needing to write our own code... significanytly reducing time and risk of error.
public interface CategoryRepository
        extends JpaRepository<Category, Long> {
}