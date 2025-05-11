package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.Category;
import de.telran.shop210125mbe.model.User;
import de.telran.shop210125mbe.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserServiceInterface userServiceInterface;

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("Get all users");
        return userServiceInterface.getAllUsers();
    }

    @GetMapping("find/{id}")
    public User getUser(@PathVariable Long id) {
        System.out.println("Get " + id + " user");
        return userServiceInterface.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        System.out.println("Insert user");
        return userServiceInterface.createUser(newUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        System.out.println("Update user");
        return userServiceInterface.updateUser(id, updatedUser);
    }

    @PatchMapping("/{id}")
    public User updatePartUser(@PathVariable Long id, @RequestBody User updatedUser) {
        System.out.println("Update user partially");
        return userServiceInterface.updatePartUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("Delete user");
        userServiceInterface.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Product controller: " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception exception) {
        return "Sorry, an error has occurred : " + exception.getMessage() + " Please try again later.";
    }
}
