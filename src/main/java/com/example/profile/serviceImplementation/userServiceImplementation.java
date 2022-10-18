package com.example.profile.serviceImplementation;

import com.example.profile.entities.User;
import com.example.profile.repository.UserRepository;
import com.example.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
