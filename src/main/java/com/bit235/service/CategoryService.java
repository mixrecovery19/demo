package com.bit235.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.bit235.model.Category;
import com.bit235.repository.CategoryRepository;

// clean example of a service class, handling the business logic for the Category entity. 
// We could have put all of this logic in the controller. However this provide for cleaner, more manageable code.
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        if (id == null) {
            return null;
        }

        return categoryRepository
                .findById(id)
                .orElse(null);
    }

    public void saveCategory(@NonNull Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(@NonNull Long id) {
        categoryRepository.deleteById(id);
    }
}