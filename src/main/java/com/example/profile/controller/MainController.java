package com.example.profile.controller;

import com.example.profile.entities.User;
import com.example.profile.helper.Message;
import com.example.profile.service.MainService;
import com.example.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.StreamHandler;

@Controller
public class MainController {


    @Autowired
    private UserService userService;

    @Autowired
    private MainService mainService;


    @GetMapping("/")
    public String user(Model model) {
        return "index";
    }

    @GetMapping("/loginpage")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/logedIn")
    public String login(HttpServletRequest request, Model model) {
        if (mainService.login(request, model)) {
            return "profile";
        }
        return "login";

    }

}
