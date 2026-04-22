package com.bit235.springtime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/form")
    public String showForm(Model model) {
        return "form";
    }

   @PostMapping("/submitUser")
public String handleUser(User user, Model model) {

    model.addAttribute("user", user);

    return "result";
}
}