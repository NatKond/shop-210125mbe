package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Product;
import de.telran.shop210125mbe.model.Role;
import de.telran.shop210125mbe.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UserServiceList implements UserServiceInterface{

    List<User> localeStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        User user1 = new User();
        user1.setUserId(1L);
        user1.setName("Alice");
        user1.setEmail("alice@example.com");
        user1.setRole(Role.ADMINISTRATOR);

        User user2 = new User();
        user2.setUserId(2L);
        user2.setName("Edward");
        user2.setEmail("edward@example.com");
        user2.setRole(Role.CLIENT);

        localeStorage.addAll(List.of(user1,user2));
    }

    @Override
    public List<User> getAllUsers() {
        return localeStorage;
    }

    @Override
    public User getUserById(Long id) {
        return localeStorage.stream().filter(user -> user.getUserId().equals(id))
                .findAny().orElseThrow(() -> new NoSuchElementException("User with id = " + id + " is not found."));
    }

    @Override
    public User createUser(User newUser) {
        if (newUser.getUserId() != null &&
                localeStorage.stream()
                        .noneMatch(user -> user.getUserId().equals(newUser.getUserId() ))) {
            localeStorage.add(newUser);
            return getUserById(newUser.getUserId());
        }
        throw new RuntimeException("User is not created. Either id = null or there is already a user with this id.");
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        for (int i = 0; i < localeStorage.size(); i++) {
            if (localeStorage.get(i).getUserId().equals(id)) {
                localeStorage.set(i, updatedUser);
                return localeStorage.get(i);
            }
        }
        return createUser(updatedUser);
    }

    @Override
    public User updatePartUser(Long id, User updatedUser) {
        for (User user : localeStorage) {
            if (user.getUserId().equals(id)) {
                if (user.getUserId() != null &&
                        !updatedUser.getUserId().equals(user.getUserId())) {
                    user.setUserId(updatedUser.getUserId());
                }
                if (user.getName() != null &&
                        !updatedUser.getName().equals(user.getName())) {
                    user.setName(updatedUser.getName());
                }
                if (user.getEmail() != null &&
                        !updatedUser.getEmail().equals(user.getEmail())) {
                    user.setEmail(updatedUser.getEmail());
                }
                if (user.getRole() != null &&
                        !updatedUser.getRole().equals(user.getRole())) {
                    user.setRole(updatedUser.getRole());
                }
                if (user.getPhoneNumber() != null &&
                        !updatedUser.getPhoneNumber().equals(user.getPhoneNumber())) {
                    user.setPhoneNumber(updatedUser.getPhoneNumber());
                }
                return user;
            }
        }
        throw new IllegalArgumentException("User with id = " + id + " is not found.");
    }

    @Override
    public void deleteUserById(Long id) {
        if (localeStorage.removeIf(user -> user.getUserId().equals(id))) return;
        throw new IllegalArgumentException("User with id = " + id + " is not found.");
    }
}
