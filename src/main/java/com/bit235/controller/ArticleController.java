package com.bit235.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

/* @Controller tells Spring this class handles browser requests (URLs).
Controller → handles requests
Service → business logic*/
@Controller
public class ArticleController {    
    // the use of "private final" fields are used for Dependency Injection.      
    private final ArticleService articleService;
    private final CategoryService categoryService;
    // constructor dependency injection. Simply assigns the necessary services
    // in effect "wiring" the application together. 
    // As example, if we wanted to assign a CountryService so we could assign a Country that the Article
    // was written in, or about, we would simply add this.countryService = countryService; to the constructor and add CountryService as a parameter
    // and then Spring would automatically provide it for us.
    public ArticleController(ArticleService articleService, CategoryService categoryService)
        {
            this.articleService = articleService;
            this.categoryService = categoryService;
        }
    // controller method to show an article list. We also use the session check to see if the user is logged in allowing
    // us to pass session/user information.
    @GetMapping("/articles")
    public String showArticles(Model model, HttpSession session)
    {
        // sessions object is being used to check if the user is logged in and to pass user information to view
        // this is common practise in many controllers.
        Person user = (Person) session.getAttribute("user");

        //debugging and testing... left in place for visual representation of how session information is being used.
        System.out.println("LOGGED USER: " + (user != null ? user.getUsername() : "Guest"));        
    // debugging and testing... also as an example of how a method can be written and the objects do not need articleService.getAllArticles() 
    // that Java allows for both variations even articleService.getAllArticles().size() is valid.
        System.out.println("ARTICLES FOUND: " 
            + articleService
                    .getAllArticles()
                    .size()
        );
       // attaches attributes to the model so that they can be accessed by Thymeleaf templates and therefore displayed in a browser.
        model.addAttribute("user", user);       
        model.addAttribute("articles", articleService.getAllArticles());  
        model.addAttribute("isFiltered", false);     
       // notice no redirect here. This is because we do not need to worry about form submissions or security checks for this controller method.
        return "articleList";
    }
    // controller method to show new article form. ensures user is logged in before granting access to the form.    
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
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
        // empty article object ready to bind form data to... without this the form would be
        // an empty page with no way to submit data. This is a common pattern in Spring MVC when using forms.
        model.addAttribute("article", new Article());      
        //Load categories so they can appear in a dropdown.
        model.addAttribute("categories", categoryService.getAllCategories());        

        return "articleForm";
    }
// controller method to handle article form sumission. It also ensures that the user is logged in and that the articles authoer is assigned correctly.
    @PostMapping("/articles/save")
    public String saveArticle(Article article, HttpSession session)
    {
        Person user = (Person) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }      
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
            // Preserve original author. Prevents users from changing article ownership during edit.             
            article.setAuthor(existingArticle.getAuthor());
            
        }
        // Service layer handles save logic. 
        articleService.saveArticle(article);       

        return "redirect:/articles";
    }
// controller method to show edit article with a dynamic path variable.
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
     // permission check, perfect example of how we can use session information to grant access to features or deny them
     // it also allows for a more personalized user experience by showing specific information based on the user.
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
    // controller method for deleting an article, with a dynamic path variable.
    // we also use redirect to ensure there is no duplication of form submissions.
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
    // controller method to view article with a dynamic path variable.
    // importantly checks to ensure the user is logged in and
    @GetMapping("/articles/{id}")
    public String viewArticle(
            @PathVariable Long id,
            Model model,
            HttpSession session
    ) {
        if (id == null) {
            return "redirect:/articles";
        }
        Person user =
                (Person) session
                        .getAttribute("user");
        Article article =
                articleService
                        .getArticleById(id);

        if (article == null) {
            return "redirect:/articles";
        }
        model.addAttribute("user", user);       
        model.addAttribute("article", article);      

        return "articleView";
    }
    // 
    @GetMapping("/articles/category/{id}")
    public String articlesByCategory(@PathVariable Long id, Model model, HttpSession session)
    {
        Person user =
                (Person) session
                        .getAttribute("user");

        model.addAttribute("user", user);
// can wrote our method calls like this... or on the same line. I personally prefer on the one line where possible
// I do not know what industry standard is.
        model.addAttribute(
                "articles",
                articleService
                        .getArticlesByCategory(id)
        );
        model.addAttribute("isFiltered", true);

        return "articleList";
    }
}