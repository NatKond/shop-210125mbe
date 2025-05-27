package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserLimitedDto;
import de.telran.shop210125mbe.pojo.User;
import de.telran.shop210125mbe.service.userService.UserServiceInterface;
import de.telran.shop210125mbe.service.userService.UserServiceJpa;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // будет создан кнструктор, аргументом которого будет поле private final
@RestController
@RequestMapping(value = "/user")
public class UserController {

//    @Autowired
//    @Qualifier("userServiceJpa")
//    @NonNull
//    private final UserServiceInterface userServiceInterface;

    private final UserServiceJpa userServiceJpa;

    @GetMapping
    public List<UserLimitedDto> getAllUsers() {
        System.out.println("Get all users");
        return userServiceJpa.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        System.out.println("Get " + id + " user");
        return userServiceJpa.getUserById(id);
    }

    @GetMapping("/email/{valueEmail}")  // http://localhost:8080/user/email/a@i.com
    public  UserDto getByEmail(@PathVariable String valueEmail) {
        return userServiceJpa.getByEmail(valueEmail);
    }

    @GetMapping("/name/{valueName}")  // http://localhost:8080/user/name/Alice%20Johnson
    public  List<UserDto> getByName(@PathVariable String valueName) {
        return userServiceJpa.getByName(valueName);
    }

    @GetMapping("/phone/{valuePhone}") // http://localhost:8080/user/phone/%201234567890
    public UserDto getByPhoneNumber(@PathVariable String valuePhoneNumber){
        return userServiceJpa.getByPhone(valuePhoneNumber);
    }

    @GetMapping("/find") // localhost:8080/user/find?name=Alice&email=alice.johnson@example.com
    public List<UserLimitedDto> getByNameAndEmail(@RequestParam String name, @RequestParam(name="email") String valueEmail){
        return userServiceJpa.getByNameAndEmail(name, valueEmail);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@RequestBody UserDto newUserDto) {
        System.out.println("Insert user");
        return userServiceJpa.createUser(newUserDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        System.out.println("Update user");
        return userServiceJpa.updateUser(id, updatedUserDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public UserDto updatePartUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        System.out.println("Update user partially");
        return userServiceJpa.updatePartUser(id, updatedUserDto);
    }

    @PatchMapping("/phone/{id}") // http://localhost:8080/user/phone/1?phone=+1234567890&name=Odarka
    public UserLimitedDto updatePhoneNumber(@PathVariable Long id, @RequestParam String phoneNumber) {
        System.out.println("Update user phone number");
        return userServiceJpa.updatePhoneNumber(id, phoneNumber);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("Delete user");
        userServiceJpa.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Product controller: " + exception.getMessage();
    }
}
