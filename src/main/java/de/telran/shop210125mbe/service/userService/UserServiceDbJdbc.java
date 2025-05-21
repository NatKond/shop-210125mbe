package de.telran.shop210125mbe.service.userService;

import de.telran.shop210125mbe.jdbc.UserDbInterface;
import de.telran.shop210125mbe.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("userJdbc")
public class UserServiceDbJdbc implements UserServiceInterface {

    @Autowired
    UserDbInterface userDbInterface;

    @Override
    public List<User> getAllUsers() {
        try {
            return userDbInterface.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Users are not found.");
        }
    }

    @Override
    public User getUserById(Long id) {
        try {
            return userDbInterface.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("User with id = " + id + " is not found.");
        }
    }

    @Override
    public User createUser(User newUser) {
        try {
            return userDbInterface.save(newUser);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("User is not created. Either id = null or there is already a user with this id.");
        }

    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        try {
            return userDbInterface.update(id, updatedUser);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("User is not created. Either id = null or there is already a user with this id.");
        }
    }

    @Override
    public User updatePartUser(Long id, User updatedUser) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        if (userDbInterface.delete(id)) return;
        throw new NoSuchElementException("User with id = " + id + " is not found.");
    }
}
