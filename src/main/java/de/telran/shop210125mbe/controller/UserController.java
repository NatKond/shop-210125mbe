package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.User;
import de.telran.shop210125mbe.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserServiceInterface userServiceInterface;

    @GetMapping
    public List<User> getAllUsers(){
        System.out.println("Get all users");
        return userServiceInterface.getAllUsers();
    }

    @PostMapping
    public void insertUser(){
        System.out.println("Insert user");
    }

    @PutMapping
    public void updateUser(){
        System.out.println("Update user");
    }

    @DeleteMapping
    public void deleteUser(){
        System.out.println("Delete user");
    }
}
