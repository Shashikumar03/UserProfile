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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MainController {


    @Autowired
  private  UserService userService;

    @GetMapping("/")
    public String user(Model model) {
        return "index";
    }


    @PostMapping("/logedIn")
    public String login(HttpServletRequest request, Model model) {
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        if (userService.emailMatched(email) && userService.passwordMatch(password)) {
            User user = userService.getUserByEmail(email);
            model.addAttribute("user", user);
        } else {
            model.addAttribute("message", "invalid credential");
            return "login";
        }
        return "profile";
    }

}
