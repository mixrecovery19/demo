package com.bit235.controller;

import com.bit235.model.Person;
import com.bit235.service.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final PersonService personService;

    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    private boolean isAdmin(HttpSession session) {
        Person user =
                (Person) session.getAttribute("user");

        return user != null
                && "1".equals(user.getUsername());
    }

    @GetMapping("/admin")
    public String adminDashboard(
            Model model,
            HttpSession session
    ) {

        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        Person user =
                (Person) session
                        .getAttribute("user");

        model.addAttribute(
                "user",
                user
        );

        model.addAttribute(
                "users",
                personService.findAll()
        );

        return "adminDashboard";
    }

   @GetMapping("/admin/users")
    public String manageUsers(
            Model model,
            HttpSession session
    ) {

        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        Person user =
                (Person) session
                        .getAttribute("user");

        model.addAttribute(
                "user",
                user
        );

        model.addAttribute(
                "users",
                personService.findAll()
        );

        return "manageUsers";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String editUserForm(
            @PathVariable Long id,
            Model model,
            HttpSession session
    ) {

        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        Person user =
                (Person) session
                        .getAttribute("user");

        model.addAttribute(
                "user",
                user
        );

        model.addAttribute(
                "person",
                personService.findById(id)
        );

        return "editUser";
    }

    @PostMapping("/admin/users/update")
    public String updateUser(
            @ModelAttribute Person person,
            HttpSession session
    ) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        personService.saveExistingUser(person);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(
            @PathVariable Long id,
            HttpSession session
    ) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        personService.deleteById(id);

        return "redirect:/admin/users";
    }
}
