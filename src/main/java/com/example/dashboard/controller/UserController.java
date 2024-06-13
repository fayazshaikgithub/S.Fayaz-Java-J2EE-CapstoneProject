package com.example.dashboard.controller;

import com.example.dashboard.model.User;
import com.example.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-details")
    public String showUserDetailsForm(Model model) {
        model.addAttribute("user", new User());
        return "user-details";
    }

    @PostMapping("/save-details")
    public String saveUserDetails(@ModelAttribute User user) {
        // Call the reactive service method to save the user details
        userService.createUser(user).subscribe();

        // Redirect to a success page or back to the form
        return "redirect:/api/users/user-details";
    }
}
