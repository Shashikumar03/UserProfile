package com.example.profile.controller;

import com.example.profile.entities.User;
import com.example.profile.helper.Message;
import com.example.profile.repository.UserRepository;
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
public class mainController {
    

    @Autowired
   UserService userService;

    @GetMapping("/")
    public String user(Model model) {
        return "index";
    }
    @GetMapping("/user")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        try {
            if (userService.nameMatched(user.getName())) {
                model.addAttribute("user", user);
                throw new Exception(" Name-already-exits");
            }
            if (userService.emailMatched(user.getEmail())) {
                model.addAttribute("user", user);
                throw new Exception(" Email-already-exits");
            }
            if (result.hasErrors()) {
                model.addAttribute("user", user);
                return "user";
            }
            userService.saveUser(user);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("successfull", "alert-success"));
            return "user";
        } catch (Exception e) {
            model.addAttribute("user", user);
            session.setAttribute("message", new Message(" Server error !! " + e.getMessage(), "alter-danger"));
            return "user";
        }
    }
    @GetMapping("/loginpage")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/logedIn")
    public String login(HttpServletRequest request, Model model) {
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        if (userService.emailMatched(email) && userService.passwordMatch(password)) {
            User user = userService.getUserByEmail(email);
            model.addAttribute("user", user);
        } else {
            return "login";
        }
        return "profile";
    }
    @GetMapping("/editprofile/{id}")
    public String editProfile(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editprofile";
    }

    @PostMapping("/updated/profile/{id}")
    public String updatedProfile(@Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable("id")
    int id, Model model, HttpSession session) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("user", user);
                return "editprofile";
            }
            userService.saveUser(user);
        } catch (Exception e) {
            model.addAttribute("user", user);
            session.setAttribute("message", new Message(" Server error !! " + e.getMessage(), "alter-danger"));
        }
          User user1=   userService.getUserById(id);
        model.addAttribute("user", user1);
        return "profile";
    }
}
