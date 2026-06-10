package com.bit235.controller;

// Spring MVC imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

// Project imports
import com.bit235.model.Category;
import com.bit235.model.Person;
import com.bit235.service.CategoryService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/// EXAMPLE OF HOW COMMEMTS AND CAN BE WRITTEN WITH A.I. Assistance. 
// I have written the comments in a more "essay" style, as opposed to the more "code-like" comments in the other files. 
// I have done this to show how comments can be written in different styles and formats, and to show that there is no one "right" way to write comments. The important thing is that the comments are clear, concise, and helpful to the reader. I have also included some comments that are more "meta" in nature, discussing the OOP and Spring Boot concepts that are being used in the code, as well as some of the design decisions that were made. I hope this provides a useful example of how comments can be written in a variety of styles and formats.
/*
 * 

*CategoryController
 * ------------------
 * This class acts as the Controller layer in the MVC pattern.
 *
 * Responsibilities:
 * - Receives browser requests relating to Categories
 * - Sends data to the View (Thymeleaf templates)
 * - Calls the Service layer to perform business logic
 * - Controls navigation flow between pages
 *
 * OOP Concepts:
 * - Encapsulation:
 *   Category operations are delegated to CategoryService
 *   rather than handled directly here.
 *
 * - Dependency Injection:
 *   Spring automatically injects CategoryService
 *   through the constructor.
 *
 * Spring Boot Concepts:
 * - @Controller tells Spring this class handles web requests.
 * - @RequestMapping("/categories") creates a base URL.
 * - Uses Session-based access control for admin-only pages.
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    /*    
     * Spring automatically provides an instance of
     * CategoryService at runtime.
     *
     * This is an example of Dependency Injection (DI),
     * an important OOP principle that reduces tight coupling.
     * That is the correct way of saying that during run time Spring will insert
     * the "things" required to make the class work. This this case that is relatively simple passing 
     * of Category Service to the constructor which is locatedin the Servie layer
     */
    public CategoryController(CategoryService categoryService)
        {
            this.categoryService =
                    categoryService;
        }

    /*
     * Display all categories
     *
     * URL:
     * /categories
     *
     * MVC Flow:
     * Browser
     * -> Controller
     * -> Service
     * -> Repository
     * -> Database
     * -> Model
     * -> Thymeleaf View
     *
     * Model stores data temporarily so it can be displayed
     * inside the categories.html page.
     */
    //Intentionally left this with the spacing to display how else Java code can be written.
    // wheter I am right or not I do not know but I personally prefer
    // e.g. listCategories(Model model, HTTPSession session) etc. same for model.Attributes("user")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping
    public String listCategories(
            Model model,
            HttpSession session
    )
    {
        Person user =
                (Person) session
                        .getAttribute(
                                "user"
                        );

        model.addAttribute(
                "user",
                user
        );

        model.addAttribute(
                "categories",
                categoryService
                        .getAllCategories()
        );

        return "categoryList";
    }
       
        @GetMapping("/new")
        public String showCreateForm(
                Model model,
                HttpSession session
        )
        {
        Person user =
                (Person) session
                        .getAttribute(
                                "user"
                        );

        model.addAttribute(
                "user",
                user
        );

        model.addAttribute(
                "category",
                new Category()
        );

        return "categoryForm";
        }

    /*
     * Save Category
     *
     * URL:
     * POST /categories/save
     *
     * @ModelAttribute automatically converts
     * submitted HTML form data into a Category object.
     *
     * This demonstrates object binding:
     * HTML form -> Java object.
     *
     * Redirect Pattern:
     * redirect:/categories prevents duplicate form
     * submissions when the browser refreshes.
     */
  @PostMapping("/save")
    public String saveCategory(
            @ModelAttribute Category category,
            HttpSession session
    ) {

        Person user =
                (Person) session
                        .getAttribute(
                                "user"
                        );

        if (category != null && user != null){

            category.setAuthor(user);            

            categoryService
                    .saveCategory(category);
        }

        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String editCategory(
            @PathVariable Long id,
            Model model,
            HttpSession session
    ) {

        Person user =
                (Person) session
                        .getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Category category =
                categoryService
                        .getCategoryById(id);

        boolean isAdmin =
                Boolean.TRUE.equals(
                        user.getIsAdmin()
                );

        boolean isOwner =
                category.getAuthor() != null
                && category.getAuthor()
                        .getId()
                        .equals(user.getId());

        if (!isAdmin && !isOwner) {
            return "redirect:/categories";
        }

        model.addAttribute(
                "category",
                category
        );
        model.addAttribute(
                "user",
                user
        );

        return "categoryForm";
    }

   // amended this method's comments as intitially I had it set up so that there was no way to edit the Category.
   // that changed and this Delete was introduced and piped accordingly.
   @GetMapping("/delete/{id}")
    public String deleteCategory(
            @PathVariable @NonNull Long id,
            HttpSession session
    ) {

        Person user =
                (Person) session
                        .getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Category category =
                categoryService
                        .getCategoryById(id);

        boolean isAdmin =
                Boolean.TRUE.equals(
                        user.getIsAdmin()
                );

        boolean isOwner =
                category.getAuthor() != null
                && category.getAuthor()
                        .getId()
                        .equals(user.getId());

        if (!isAdmin && !isOwner) {
            return "redirect:/categories";
        }

        categoryService
                .deleteCategory(id);

        return "redirect:/categories";
    }
}