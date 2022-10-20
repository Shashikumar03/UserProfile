package com.example.profile.serviceImplementation;

import com.example.profile.entities.User;
import com.example.profile.helper.Message;
import com.example.profile.repository.UserRepository;
import com.example.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

@Service
public class userServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean nameMatched(String name) {
        if (userRepository.existsByName(name)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean emailMatched(String email) {
        if (userRepository.existsByEmail(email)) {
            return true;
        }
        return false;
    }
    @Override
    public boolean passwordMatch(String password) {
        if (userRepository.existsByPassword(password)) {
            return true;
        }
        return false;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public String loginVerification(String email, String pass) {
        if (userRepository.existsByEmail(email) && userRepository.existsByPassword(pass)) {
            return "profile";
        } else {
            return "login";
        }

    }

    @Override
    public String register(User user, BindingResult result, Model model, HttpSession session) {
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
            session.setAttribute("message", new Message(" Server error !! "
                    + e.getMessage(), "alter-danger"));
            return "user";
        }
    }

    @Override
    public String updatedProfile(User user, BindingResult result, int id, Model model, HttpSession session) {
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
        User user1 = userRepository.findById(id).get();
        model.addAttribute("user", user1);
        return "profile";
    }

}
