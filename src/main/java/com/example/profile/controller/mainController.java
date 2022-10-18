package com.example.profile.controller;

import com.example.profile.entities.User;
import com.example.profile.helper.Message;
import com.example.profile.repository.UserRepository;
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
import java.util.List;

@Controller
public class mainController {

    @Autowired
    UserRepository userRepository;

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
            if (userRepository.existsByName(user.getName())) {
                model.addAttribute("user", user);
                throw new Exception(" Name-already-exits");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                model.addAttribute("user", user);
                throw new Exception(" Email-already-exits");
            }
            if (result.hasErrors()) {
                model.addAttribute("user", user);
                return "user";
            }
            userRepository.save(user);
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
        if (userRepository.existsByEmail(email) && userRepository.existsByPassword(password)) {
            User user = userRepository.findByEmail(email);
            model.addAttribute("user", user);
        } else {
            return "login";
        }
        return "profile";
    }
    @GetMapping("/editprofile/{id}")
    public String editProfile(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
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
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("user", user);
            session.setAttribute("message", new Message(" Server error !! " + e.getMessage(), "alter-danger"));
        }
          User user1=   userRepository.findById(id).get();
        model.addAttribute("user", user1);
        return "profile";
    }
}
