package com.bit235.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bit235.model.Category;
import com.bit235.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
            CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(
            Model model
    ) {

        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        return "categories";
    }

    @GetMapping("/new")
    public String showCreateForm(
            Model model,
            HttpSession session
    ) {

        Boolean isAdmin =
                (Boolean) session.getAttribute(
                        "isAdmin"
                );

        if (isAdmin == null || !isAdmin) {
            return "redirect:/login";
        }

        model.addAttribute(
                "category",
                new Category()
        );

        return "categoryForm";
    }

    @PostMapping("/save")
    public String saveCategory(
            @ModelAttribute Category category
    ) {

        categoryService.saveCategory(
                category
        );

        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(
            @PathVariable Long id,
            HttpSession session
    ) {

        Boolean isAdmin =
                (Boolean) session.getAttribute(
                        "isAdmin"
                );

        if (isAdmin == null || !isAdmin) {
            return "redirect:/login";
        }

        categoryService.deleteCategory(id);

        return "redirect:/categories";
    }
}