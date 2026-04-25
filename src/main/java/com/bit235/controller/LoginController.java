package com.bit235.controller;//maybe rename
// setting the tone, we are importing the necessary libraries for our controller and spring boot application.
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.bit235.model.User;

// setting a method to handle the home page, this is for the handle of the home page as well as login and logout while also setting the user name, password as well
// as the session, allowing for us to store user information is important so we can "remember" and make it a far more personalised experience.
@Controller
public class LoginController {
    @GetMapping("/")
        public String home(Model model, HttpSession session) {

            User user = (User) session.getAttribute("user"); // get stored user
            model.addAttribute("user", user);

            return "index";
        }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // 🔴 clears EVERYTHING in session
        return "redirect:/"; // send user back to login page
    }

    @PostMapping("/login")
    public String handleLogin(User user, Model model, HttpSession session) {

        if ("Michael".equals(user.getUsername()) &&
            "123".equals(user.getPassword())) {

            session.setAttribute("user", user);  // ✅ STORE IN SESSION
            return "redirect:/";                 // ✅ GO BACK TO HOME

        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}