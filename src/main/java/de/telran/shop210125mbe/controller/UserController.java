package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.User;
import de.telran.shop210125mbe.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    @Qualifier("userJdbc")
    UserServiceInterface userServiceInterface;

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("Get all users");
        return userServiceInterface.getAllUsers();
    }

    @GetMapping("/{id}")
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
}
