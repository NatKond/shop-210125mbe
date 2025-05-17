package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User newUser);

    User updateUser(Long id, User updatedUser);

    User updatePartUser(Long id, User updatedUser);

    void deleteUserById(Long id);

}
