package de.telran.shop210125mbe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserLimitedDto;
import de.telran.shop210125mbe.service.userService.UserServiceJpa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // для имитации запросов пользователя

    @MockitoBean
    private UserServiceJpa userServiceJpa;

    @Autowired
    private ObjectMapper objectMapper; // для преобразования объекта в Json

    @Test
    void getAllUsersTest() throws Exception {
        //given
        List<UserLimitedDto> expected = List.of(
                new UserLimitedDto(1L, "TestName1", "test1@i.com", "+49111222222222"),
                new UserLimitedDto(2L, "TestName2", "test2@i.com", "+49111333333333")
        );

        //when
        when(userServiceJpa.getAllUsers()).thenReturn(expected);

        //then
        mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userId").exists());// проверят существует ли в json тег userId
        //.andExpect(jsonPath("$..userId").value(1)) // проветяет что поле userId = 1
        //.andExpect(jsonPath("$..name").value("TestName1")); // проветяет что поле name = TestName1
    }

    @Test
    void getUserTest() throws Exception {
        UserDto expected = UserDto.builder()
                .userId(1L)
                .name("TestName1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .build();


        when(userServiceJpa.getUserById(anyLong())).thenReturn(expected);
        // anyLong - при педече любого объекта типа Long будет возвращаться объект
        mockMvc.perform(get("/user/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(1)) // проветяет что поле userId = 1
                .andExpect(jsonPath("$.name").value("TestName1") // проветяет что поле name = TestName1
                );
    }

    @Test
    void getByEmailTest() throws Exception {
        UserDto expected = UserDto.builder()
                .userId(1L)
                .name("TestName1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .build();
        when(userServiceJpa.getByEmail(anyString())).thenReturn(expected);

        // anyString - при педече любого объекта типа String будет возвращаться объект
        mockMvc.perform(get("/user/email/{valueEmail}", "test1@i.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.email").value("test1@i.com")
                );
    }

    @Test
    void getByNameTest() throws Exception {
        List<UserDto> expected = List.of(
                UserDto.builder()
                        .userId(1L)
                        .name("TestName")
                        .email("test1@i.com")
                        .phoneNumber("+49111222222222")
                        .build(),
                UserDto.builder()
                        .userId(2L)
                        .name("TestName")
                        .email("test2@i.com")
                        .phoneNumber("+49111333333333")
                        .build()
        );
        when(userServiceJpa.getByName(anyString())).thenReturn(expected);

        // anyString - при педече любого объекта типа String будет возвращаться объект
        mockMvc.perform(get("/user/name/{valueName}", "TestName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..name").exists()
                //.andExpect(jsonPath("$..name").value("TestName")
                );
    }

    @Test
    void getByPhoneNumberTest() throws Exception {
        UserDto expected = UserDto.builder()
                .userId(1L)
                .name("TestName1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .build();
        when(userServiceJpa.getByPhone("+49111222222222")).thenReturn(expected);

        // anyString - при педече любого объекта типа String будет возвращаться объект
        mockMvc.perform(get("/user/phone/{valuePhone}", "+49111222222222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").exists())
                .andExpect(jsonPath("$.phoneNumber").value("+49111222222222")
                );
    }

    @Test
    void getByNameAndEmailTest() {
    }

    @Test
    void createUserTest() throws Exception {
        Long idExpected = 1L;
        UserDto expected = UserDto.builder()
                .userId(idExpected)
                .name("TestName")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .build();
        when(userServiceJpa.createUser(any(UserDto.class))).thenReturn(expected);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                     "userId": null,
                                     "name": "TestName"
                                }
                                """
                        ))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(idExpected))
                .andExpect(jsonPath("$.name").value("TestName")
                );
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto expected = UserDto.builder()
                .userId(1L)
                .name("TestName1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .build();

        when(userServiceJpa.updateUser(expected.getUserId(), expected)).thenReturn(expected);
        mockMvc.perform(put("/user/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)
                        ))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.name").value("TestName1")
                );
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