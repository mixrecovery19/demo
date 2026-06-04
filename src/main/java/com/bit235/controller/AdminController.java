package com.bit235.controller;
import com.bit235.model.Person;
import com.bit235.service.PersonService;

import jakarta.servlet.http.HttpSession;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Class to handle admin... controller "directs traffic".
// left without heavy comments to show a neat Java class.
        @Controller
        public class AdminController {

        private final PersonService personService;

        public AdminController(PersonService personService) {
                this.personService = personService;
        }
// Helper method to check if the user is admin or not.
        private boolean isAdmin(HttpSession session) {
                Person user =
                        (Person) session
                                .getAttribute("user");
                return user != null
                        && Boolean.TRUE.equals(user.getIsAdmin());                        
                }
 // method to map a admin dashboard while also checking to ensure the user does have admin privileges.
        @GetMapping("/admin")
        public String adminDashboard(Model model, HttpSession session)
                {
                        if (!isAdmin(session)) {
                        return "redirect:/login";
                        }

                        Person user = (Person) session.getAttribute("user");
                        model.addAttribute("user", user);       
                        model.addAttribute("users", personService.findAll());        

                        return "adminDashboard";
                }
// method to map the management of users. Again, also checks to ensure the user has admin privileges.
        @GetMapping("/admin/users")
        public String manageUsers(Model model, HttpSession session)
                {
                        if (!isAdmin(session)) {
                        return "redirect:/login";
                        }

                        Person user = (Person) session.getAttribute("user");

                        model.addAttribute("user", user);           
                        model.addAttribute("users", personService.findAll());        

                        return "manageUsers";
                }
// method to map the edit user form with a dynamic path variable. Also checks to ensure the user has admin privileges.
        @GetMapping("/admin/users/edit/{id}")
        public String editUserForm(@PathVariable @NonNull Long id, Model model, HttpSession session)
                {
                        if (!isAdmin(session)) {
                        return "redirect:/login";
                        }
                        Person user = (Person) session.getAttribute("user");

                        model.addAttribute("user", user);       
                        model.addAttribute("person", personService.findById(id));        

                        return "editUser";
                }

// method to handle the update user form submission. Also checks to ensure the user has admin privileges.
        @PostMapping("/admin/users/update")
        public String updateUser(@ModelAttribute Person person, HttpSession session)
                {
                        if (!isAdmin(session)) {
                        return "redirect:/login";
                        }

                        personService.saveExistingUser(person);

                        return "redirect:/admin/users";
                }

// method to handle the delete user request. Also checks to ensure the user has admin privileges.
// left heavy print statements, initially for debugging, now for demonstration purposes.
        @GetMapping("/admin/users/delete/{id}")
        public String deleteUser(@PathVariable @NonNull Long id)
                {
                        System.out.println("=================================");   
                        System.out.println("DELETE BUTTON REACHED CONTROLLER");   
                        System.out.println("USER ID TO DELETE: " + id);    

                        personService.deleteById(id);

                        System.out.println("CONTROLLER FINISHED");
                        System.out.println("=================================");    

                        return "redirect:/admin/users";
                }
}
