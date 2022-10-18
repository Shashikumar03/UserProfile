package com.example.profile.repository;


import com.example.profile.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByName(String name);
    public boolean existsByName(String name);
    public boolean existsByEmail(String email);
    public boolean existsByPassword(String password);

   public User findByEmail(String email);



}