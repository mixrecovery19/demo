/*package com.bit235.controller;

// Spring MVC imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

/*
 * CountryController
 * -----------------
 * This class acts as a simple MVC Controller.
 *
 * Responsibilities:
 * - Respond to requests for the Country page
 * - Pass session information to the View
 * - Return the correct Thymeleaf template
 *
 * Spring Boot Concepts:
 * - @Controller tells Spring this class handles web requests.
 * - @GetMapping maps a browser URL to a method.
 *
 * MVC Role:
 * This Controller sits between:
 *
 * Browser
 * -> Controller
 * -> Model
 * -> Thymeleaf View
 *
 * Since this page does not currently require
 * database interaction, no Service or Repository
 * layer is used here.
 */
/*@Controller
public class CountryController {

    /*
     * Display Country Page
     *
     * URL:
     * /country
     *
     * Session Handling:
     * Retrieves the isAdmin session value
     * and passes it to the HTML page.
     *
     * This allows Thymeleaf to conditionally
     * show or hide Admin-only features.
     *
     * Model:
     * Used to temporarily store data that
     * becomes accessible inside country.html.
     */
    /*
    @GetMapping("/country")
    public String country(Model model, HttpSession session)
    {
        model.addAttribute("isAdmin", session.getAttribute("isAdmin"));        
        // Return Thymeleaf page:
        // country.html
        return "country";
    }
}*/
