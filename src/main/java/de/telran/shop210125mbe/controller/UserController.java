package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserLimitedDto;
import de.telran.shop210125mbe.service.userService.UserServiceJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.*;

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
        System.out.println(YELLOW + "Get all users" + RESET);
        return userServiceJpa.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        System.out.println(YELLOW + "Get " + id + " user" + RESET);
        return userServiceJpa.getUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email/{valueEmail}")  // http://localhost:8080/user/email/a@i.com
    public UserDto getByEmail(@PathVariable String valueEmail) {
        System.out.println(YELLOW + "Get user with email = " + valueEmail + RESET);
        return userServiceJpa.getByEmail(valueEmail);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{valueName}")  // http://localhost:8080/user/name/Alice%20Johnson
    public List<UserDto> getByName(@PathVariable String valueName) {
        System.out.println(YELLOW + "Get user with name = " + valueName + RESET);
        return userServiceJpa.getByName(valueName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/phone/{valuePhone}") // http://localhost:8080/user/phone/%201234567890
    public UserDto getByPhoneNumber(@PathVariable String valuePhone) {
        System.out.println(YELLOW + "Get user with phone = " + valuePhone + RESET);
        return userServiceJpa.getByPhone(valuePhone);
    }

    @GetMapping("/find") // localhost:8080/user/find?name=Alice&email=alice.johnson@example.com
    public List<UserLimitedDto> getByNameAndEmail(@RequestParam String name, @RequestParam(name = "email") String valueEmail) {
        System.out.println(YELLOW + "Get user with name = " + name + " and email = " + valueEmail + RESET);
        return userServiceJpa.getByNameAndEmail(name, valueEmail);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@RequestBody UserDto newUserDto) {
        System.out.println(YELLOW + "Insert user" + RESET);
        return userServiceJpa.createUser(newUserDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        System.out.println(YELLOW + "Update user" + RESET);
        return userServiceJpa.updateUser(id, updatedUserDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public UserDto updatePartUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        System.out.println(YELLOW + "Update user partially" + RESET);
        return userServiceJpa.updatePartUser(id, updatedUserDto);
    }

    @PatchMapping("/phone/{id}") // http://localhost:8080/user/phone/1?phone=+1234567890&name=Odarka
    public UserLimitedDto updatePhoneNumber(@PathVariable Long id, @RequestParam String phoneNumber) {
        System.out.println(YELLOW + "Update user phone number" + RESET);
        return userServiceJpa.updatePhoneNumber(id, phoneNumber);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println(YELLOW + "Delete user" + RESET);
        userServiceJpa.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Product controller: " + exception.getMessage();
    }
}
