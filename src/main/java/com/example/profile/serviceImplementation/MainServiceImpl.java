package com.example.profile.serviceImplementation;

import com.example.profile.repository.UserRepository;
import com.example.profile.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    UserRepository userRepository;

    public boolean login(HttpServletRequest request, Model model) {
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
         if(userRepository.existsByEmail(email) && userRepository.existsByPassword(password)){
             model.addAttribute("user", userRepository.findByEmail(email));
         }
         else {
             model.addAttribute("message", "invalid credential");
             return false;
         }
        return true;
    }

}
