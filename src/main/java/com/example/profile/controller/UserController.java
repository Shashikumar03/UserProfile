package com.example.profile.controller;

import com.example.profile.entities.User;
import com.example.profile.helper.Message;
import com.example.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result,
                           Model model, HttpSession session) {
          return userService.register(user, result, model, session);
    }


    @GetMapping("/editprofile/{id}")
    public String editProfile(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editprofile";
    }

    @PostMapping("/updated/profile/{id}")
    public String updatedProfile(@Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable("id")
    int id, Model model, HttpSession session) {
     return  userService.updatedProfile(user,result,id,model, session);
    }
}
