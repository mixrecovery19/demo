package com.bit235.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class CountryController {
    @GetMapping("/country")
public String country(Model model, HttpSession session) {

    model.addAttribute(
            "isAdmin",
            session.getAttribute("isAdmin")
    );

    return "country";
}
}
