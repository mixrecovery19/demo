package com.bit235.controller;//maybe rename
// setting the tone, we are importing the necessary libraries for our controller and spring boot application.
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bit235.model.User;

// setting a method to handle the home page, this is for the handle of the home page as well as login and logout while also setting the user name, password as well
// as the session, allowing for us to store user information is important so we can "remember" and make it a far more personalised experience.
@Controller
public class LoginController {
    @GetMapping("/")
        public String home(Model model, HttpSession session) { // get session information, handy, if not needed for smooth user experience for stage 2.

            User user = (User) session.getAttribute("user"); // get stored user
            model.addAttribute("user", user); 

            return "index"; // returns the home page... this is Java "magic" and it will look for index.html in the templates folder, 
            // combine this with things like the HttpSession and Model and we can get things like personalied greeting and other user specifici info.
        }

    @GetMapping("/login")
    public String showLogin() {
        return "login"; //directs to login page.
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // 🔴 clears EVERYTHING in session
        return "redirect:/"; // send user back to login page
    }
// PASSWORD J4vaCl4ss! USERNAME Michael - Nice and easy.
    @PostMapping("/login") // handles the login submissions etc. With Database or the like, possibly 3rd party oauth we could have more complex logic here
    // we could do things like create other folders, search database for user info, even apply API calls to 3rd party services for things like 2FA.
    public String handleLogin(User user, Model model, HttpSession session) {

        if ("Michael".equals(user.getUsername()) &&
            "J4v4Cl4ss!".equals(user.getPassword())) {

            session.setAttribute("user", user);  // ✅ STORE IN SESSION
            return "redirect:/";                 // ✅ GO BACK TO HOME

        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}