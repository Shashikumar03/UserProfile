package com.example.profile.service;

import com.example.profile.entities.User;
import com.example.profile.helper.Message;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public interface UserService {


    public User getUserByEmail(String email);

    public User getUserById(int id);

    public boolean nameMatched(String name);

    public boolean emailMatched(String email);

    public boolean passwordMatch(String password);

    public void saveUser(User User);


    public String loginVerification( String email, String pass);

    public String register(@Valid @ModelAttribute("user") User user, BindingResult result,
                           Model model, HttpSession session);

    public String updatedProfile(@Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable("id")
    int id, Model model, HttpSession session) ;




}
