package com.bit235.controller;

// Spring MVC imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

// Project imports
import com.bit235.model.Category;
import com.bit235.service.CategoryService;

import jakarta.servlet.http.HttpSession;

/*
 * CategoryController
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
    @GetMapping
    public String listCategories(Model model)
        {
            model.addAttribute("categories", categoryService.getAllCategories());        

            return "categories";
        }

    /*
     * Display Create Category Form
     *
     * URL:
     * /categories/new
     *
     * Session Handling:
     * Checks whether the logged-in user is an Admin.
     *
     * If not admin:
     * redirect user to login page.
     *
     * If admin:
     * provide an empty Category object for the form.
     *
     * OOP:
     * new Category() creates an object instance
     * which Thymeleaf binds to the HTML form.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model, HttpSession session)
        {
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");              

            // Basic access control
            if (isAdmin == null || !isAdmin) {
                return "redirect:/login";
            }
            model.addAttribute("category", new Category());        

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
    public String saveCategory(@ModelAttribute Category category) 
        {
            // Defensive check
            if (category != null) {
                categoryService.saveCategory(category);            
            }
            return "redirect:/categories";
        }

    // delete category by id is not really set up in place
    // it allows for a category to be deleted throught the article managment yet there is
    // no category management page, which is something we could easily add with scale
    // this project only allows the admin to delete users, articles, categories are fixed and there is intentionally no front
    // end way that a admin can delete or edit a category.
    // we still have the method in place and we can also see the use of both @PathVariable, @NotNull and also the redirect 
    // flow which prevents duplicate form submissions and also allows for a better user experience as well as security reasons.
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable @NonNull Long id, HttpSession session)
    {

        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");               

        // Restrict access to Admin users only
        if (isAdmin == null || !isAdmin) {
            return "redirect:/login";
        }
        categoryService.deleteCategory(id);

        return "redirect:/categories";
    }
}