package com.example.profile.service;

import com.example.profile.entities.User;

public interface UserService {


    public User getUserByEmail(String email);

    public User getUserById(int id);

    public boolean nameMatched(String name);

    public boolean emailMatched(String email);

    public boolean passwordMatch(String password);

    public void saveUser(User User);


}
