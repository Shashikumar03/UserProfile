package com.example.profile.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;


public interface MainService {
    public boolean login(HttpServletRequest request, Model model);
}
