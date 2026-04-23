package com.bit235.springtime;//maybe rename

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FormController {
    @GetMapping("/")
        public String home(Model model, HttpSession session) {

            User user = (User) session.getAttribute("user"); // get stored user
            model.addAttribute("user", user);

            return "home";
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

        if ("admin".equals(user.getUsername()) &&
            "admin123".equals(user.getPassword())) {

            session.setAttribute("user", user);  // ✅ STORE IN SESSION
            return "redirect:/";                 // ✅ GO BACK TO HOME

        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}