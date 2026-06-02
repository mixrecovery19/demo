/* this is a login controller, spring boot uses controllers to handle https requests and map the urls
 */
package com.bit235.controller;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.core.io.Resource;
import java.util.Arrays;
import java.util.List;

import com.bit235.model.Article;
import com.bit235.model.Person;
import com.bit235.service.PersonService;
import com.bit235.service.ArticleService;

/**
 * LoginController
 * -----------------
 * This class handles login-related requests. By access the necessary Service laeyers it can perform login flow and logic as well as, in this case
 * displaying the home page with dynamic content.
 */
@Controller
public class LoginController {

    private final PersonService personService;
    private final ArticleService articleService;

    public LoginController(PersonService personService, ArticleService articleService)
    {
        this.personService = personService;
        this.articleService = articleService;
    }

        //method to display the home page, it also passes session information to the view and loads dynamic content such as images and a featured article
        @GetMapping("/")
                public String home(Model model, HttpSession session)
                throws IOException {
        //using person object to store the user information in the session and pass it to the view
        //mildly confusing because we really must create a Person Class prior to being able to use it as an object
        Person user = 
                (Person) session.getAttribute("user");                       

                model.addAttribute("user", user);      
                model.addAttribute("isAdmin", user != null && Boolean.TRUE.equals(user.getIsAdmin()));            
        
        // loads all images so the home page can display and javascript function can access them
        Resource[] files = new PathMatchingResourcePatternResolver()
                                .getResources("classpath:/static/images/*");                     

        List<String> images =
                Arrays.stream(files)
                        .map(file -> "/images/" + file.getFilename())                        
                        .toList();

                model.addAttribute("images", images);                
        
        //introduces the Feature Article to the home page. Created in ArticleService.
        // a great visual example of how we create a method in the Service layer, then call it through the Controller layer
        // to pass on as required to eventually be displayed through Thymeleaf in the View layer. 
        Article featuredArticle =
                articleService
                        .getRandomArticle();
                        model.addAttribute("featuredArticle", featuredArticle);        

                return "index";
        }

        // 🔹 Login Page
        @GetMapping("/login")
        public String showLogin(Model model) {            
                model.addAttribute("person", new Person());                

                return "login";
        }

        // 🔹 Login Action
        @PostMapping("/login")
        public String handleLogin(Person formUser, Model model, HttpSession session)
        {
        Person loggedInUser =
                personService.login(
                        formUser.getUsername(),
                        formUser.getPassword()
                );

        if (loggedInUser != null) {

            session.setAttribute("user", loggedInUser);           
            session.setAttribute("isAdmin", loggedInUser.getIsAdmin());           

            return "redirect:/";
        }
                model.addAttribute("error", "Invalid username or password");       

                return "login";
        }

// another key part of this module was to understand what redirect does, why we use it in place of a return as well as
// what session.inavlidate() does and why we use it. Session invalidation is important for security reasons,
// it allows for us to clear the session data and let the program know that the user is no longer logged in.
// Sessions are important for feel and functionality as they prevent continual login requirments
// which actually allows for a personalized feel to your project.
// redirect is important because it in effect tells the program, Spring to use GET instead of POST.
// that is the main reason, which what that does is allows for users to be redirected through a GET request instead of a POST request, which
// would actually treat it as a form submission. So in a sense that was the long way of saying it prevents duplicate form submissions
// which is important for user experience and security reasons.
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();

        return "redirect:/";
    }
}