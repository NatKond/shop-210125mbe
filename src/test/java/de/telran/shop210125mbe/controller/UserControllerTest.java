package de.telran.shop210125mbe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // для имитации запросов

    @Test
    void getAllUsersTest() {
    }

    @Test
    void getUserTest() {
    }

    @Test
    void getByEmailTest() {
    }

    @Test
    void getByNameTest() {
    }

    @Test
    void getByPhoneNumberTest() {
    }

    @Test
    void getByNameAndEmailTest() {
    }

    @Test
    void createUserTest() {
    }

    @Test
    void updateUserTest() {
    }

    @Test
    void updatePartUserTest() {
    }

    @Test
    void updatePhoneNumberTest() {
    }

    @Test
    void deleteUserTest() {
    }

    @Test
    void handlerIllegalArgumentExceptionTest() {
    }
}