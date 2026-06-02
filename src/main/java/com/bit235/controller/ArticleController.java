package com.bit235.controller;

/*
 * ArticleController
 * ------------------------------------------------
 * This Controller handles all requests related to
 * Articles in the application.
 *
 * In MVC:
 *
 * Controller
 * → receives browser requests
 * → talks to the Service layer
 * → sends data to Thymeleaf views
 *
 * This controller manages:
 * - Viewing articles
 * - Creating articles
 * - Editing articles
 * - Deleting articles
 *
 * It also checks login permissions using
 * HttpSession to ensure only logged-in users
 * can create, edit, or delete content.
 */

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bit235.model.Article;
import com.bit235.model.Person;
import com.bit235.service.ArticleService;
import com.bit235.service.CategoryService;
import java.util.Objects;

/*
 * @Controller tells Spring this class
 * handles browser requests (URLs).
 */
@Controller
public class ArticleController {

    /*
     * final fields are used for
     * Dependency Injection.
     *
     * Spring automatically provides
     * these Service objects through
     * the constructor.
     *
     * This follows separation of concerns:
     * Controller → handles requests
     * Service → business logic
     */
    private final ArticleService articleService;
    private final CategoryService categoryService;

    /*
     * Constructor Injection
     * ------------------------
     * Spring automatically injects
     * the required services here.
     *
     * This is preferred over @Autowired
     * because it is cleaner and safer.
     */
    public ArticleController(ArticleService articleService, CategoryService categoryService)
    {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    /*
     * PUBLIC ARTICLE LIST
     * ------------------------
     * Displays all articles.
     *
     * URL:
     * /articles
     *
     * Model is used to send data
     * to the Thymeleaf template.
     */
    @GetMapping("/articles")
    public String showArticles(Model model, HttpSession session)
    {
        //sessions object is being used to check if the user is logged in and to pass user information to view
        // this is common practise in many controllers.
        Person user = (Person) session.getAttribute("user");

        //debugging and testing... left in place for visual representation of how session information is being used.
        System.out.println("LOGGED USER: " + (user != null ? user.getUsername() : "Guest"));        
//debugging and testing... also as an example of how a method can be written and the objects do not need articleService.getAllArticles() 
// that Java allows for both variations even articleService.getAllArticles().size() is valid.
        System.out.println("ARTICLES FOUND: " 
            + articleService
                    .getAllArticles()
                    .size()
        );

        /*
         * Add data to the Model so
         * Thymeleaf can access it.
         *
         * Example:
         * ${user}
         * ${articles}
         */
        model.addAttribute("user", user);       
        model.addAttribute("articles", articleService.getAllArticles());       
        /*
         * Returns articleList.html
         */
        return "articleList";
    }

    /*
     * CREATE ARTICLE FORM
     * ------------------------
     * Displays the form used to create
     * a new article.
     *
     * Login is required.
     */
    @GetMapping("/articles/new")
    public String showArticleForm(Model model, HttpSession session)
    {
        Person user = (Person) session.getAttribute("user");
        /* Security check. If not logged in, redirect to login page.
         * redirect: prevents users accessing restricted pages directly. */         
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);       

        /*
         * Empty Article object
         * is provided to bind
         * the HTML form data.
         */
        model.addAttribute("article", new Article());      
        //Load categories so they can appear in a dropdown.
        model.addAttribute("categories", categoryService.getAllCategories());        

        return "articleForm";
    }

    @PostMapping("/articles/save")
    public String saveArticle(Article article, HttpSession session)
    {
        Person user = (Person) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        /*
         * If ID is null,
         * this is a NEW article.
         *
         * Assign the current
         * logged-in user as author.
         */
        if (article.getId() == null) {
            article.setAuthor(user);
        }
        else {
            Article existingArticle =
                    articleService
                            .getArticleById(Objects.requireNonNull(article.getId()));                            

            if (existingArticle == null) {
                return "redirect:/articles";
            }            
             //Preserve original author. Prevents users from changing article ownership during edit.             
            article.setAuthor(existingArticle.getAuthor());
            
        }
        /** Service layer handles save logic. */
        articleService.saveArticle(article);       

        return "redirect:/articles";
    }

    @GetMapping("/articles/edit/{id}")
    public String editArticle(@PathVariable long id, Model model, HttpSession session)
    {

        Person user = (Person) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }        
         //PathVariable extracts the ID from the URL.        
        Article article = articleService.getArticleById(id);                    

        if (article == null) {
            return "redirect:/articles";
        }

        /*
         * Permission rule:
         *
         * User can edit if:
         * - Admin
         * OR
         * - Original author
         *
         * Good example of business rules.
         */
        boolean canEdit =
                user.getIsAdmin()
                ||
                Objects.equals(article.getAuthor().getId(), user.getId());                

        if (!canEdit) {
            return "redirect:/articles";
        }

        model.addAttribute("user", user);      
        model.addAttribute("article", article);        
        model.addAttribute("categories", categoryService.getAllCategories());        

        return "articleForm";
    }

    /*
     * DELETE ARTICLE
     * ------------------------
     * Removes an article.
     *
     * Same permission rule:
     * admin OR article author.
     */
    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id, HttpSession session)
    {
        Person user = (Person) session.getAttribute("user");

                if (user == null) {
                return "redirect:/login";
                }

                if (id == null) {
                return "redirect:/articles";
                }

        Article article = articleService.getArticleById(id);

                if (article == null) {
                return "redirect:/articles";
                }

        boolean canDelete =
                user.getIsAdmin()
                ||
                Objects.equals(article.getAuthor().getId(), user.getId());                

        if (!canDelete) {
            return "redirect:/articles";
        }
        /* try/catch prevents application crashes if deletion fails. */         
        try {

            articleService.deleteArticle(id);

        } catch (Exception e) {

            throw new IllegalStateException("Unable to delete article", e);            
        }

        return "redirect:/articles";
    }
}