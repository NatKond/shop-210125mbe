package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Role;
import de.telran.shop210125mbe.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
}
