package de.telran.shop210125mbe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserLimitedDto;
import de.telran.shop210125mbe.service.userService.UserServiceJpa;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // для имитации запросов пользователя

    @MockitoBean
    private UserServiceJpa userServiceJpa;

    @Autowired
    private ObjectMapper objectMapper; // для преобразования объекта в Json

    static UserLimitedDto userLimitedDto1;
    static UserLimitedDto userLimitedDto2;
    static UserDto userDto1;
    static UserDto userDto2;

    @BeforeAll
    static void setUp() {
        userLimitedDto1 = UserLimitedDto.builder()
                .userId(1L)
                .name("TestUser1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .build();
        userLimitedDto2 = UserLimitedDto.builder()
                .userId(2L)
                .name("TestUser2")
                .email("test2@i.com")
                .phoneNumber("+49111333333333")
                .build();
        userDto1 = UserDto.builder()
                .userId(1L)
                .name("TestUser1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .build();
        userDto2 = UserDto.builder()
                .userId(2L)
                .name("TestUser2")
                .email("test2@i.com")
                .phoneNumber("+49111333333333")
                .build();
    }

    @Test
    void getAllUsersTest() throws Exception {
        //given
        List<UserLimitedDto> expected = List.of(
                userLimitedDto1,
                userLimitedDto2
        );

        //when
        when(userServiceJpa.getAllUsers()).thenReturn(expected);

        //then
        mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(expected)));
        //.andExpect(jsonPath("$..userId").exists());// проверят существует ли в json тег userId
        //.andExpect(jsonPath("$..userId").value(1)) // проветяет что поле userId = 1
        //.andExpect(jsonPath("$..name").value("TestName1")); // проветяет что поле name = TestName1
    }

    @Test
    void getUserTest() throws Exception {
        UserDto expected = userDto1;

        when(userServiceJpa.getUserById(expected.getUserId())).thenReturn(expected);
        // anyLong - при педече любого объекта типа Long будет возвращаться объект
        mockMvc.perform(get("/user/{id}", expected.getUserId()))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.userId").exists(),
                        jsonPath("$.userId").value(expected.getUserId()), // проветяет что поле userId = 1
                        jsonPath("$.name").value(expected.getName()), // проветяет что поле name = TestName1
                        jsonPath("$.email").value(expected.getEmail()),
                        jsonPath("$.phoneNumber").value(expected.getPhoneNumber())
                );
    }

    @Test
    void getByEmailTest() throws Exception {
        UserDto expected = userDto1;
        when(userServiceJpa.getByEmail(anyString())).thenReturn(expected);

        // anyString - при педече любого объекта типа String будет возвращаться объект
        mockMvc.perform(get("/user/email/{valueEmail}", expected.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.email").value("test1@i.com")
                );
    }

    @Test
    void getByNameTest() throws Exception {
        List<UserDto> expected = List.of(
                userDto1,
                userDto2
        );
        when(userServiceJpa.getByName(userDto1.getName().substring(0,5))).thenReturn(expected);

        // anyString - при педече любого объекта типа String будет возвращаться объект
        mockMvc.perform(get("/user/name/{valueName}", userDto1.getName().substring(0,5)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(expected))
                );
    }

    @Test
    void getByPhoneNumberTest() throws Exception {
        UserDto expected = userDto1;
        when(userServiceJpa.getByPhone(anyString())).thenReturn(expected);

        // anyString - при педече любого объекта типа String будет возвращаться объект
        mockMvc.perform(get("/user/phone/{valuePhone}", expected.getPhoneNumber()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").exists())
                .andExpect(jsonPath("$.phoneNumber").value(expected.getPhoneNumber())
                );
    }

    @Test
    void getByNameAndEmailTest() throws Exception {
        UserLimitedDto expected = userLimitedDto1;
        when(userServiceJpa.getByNameAndEmail("Test","test1@i.com")).thenReturn(List.of(expected));

        mockMvc.perform(get("/user/find")
                .param("name","Test")
                .param("email","test1@i.com"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].userId").exists(),
                        jsonPath("$[0].userId").value(expected.getUserId()),
                        jsonPath("$[0].name").value(expected.getName()),
                        jsonPath("$[0].email").value(expected.getEmail())
                );
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
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.userId").exists(),
                        jsonPath("$.userId").value(idExpected),
                        jsonPath("$.name").value(expected.getName())
                );
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto expected = userDto1;

        when(userServiceJpa.updateUser(expected.getUserId(), expected)).thenReturn(expected);
        mockMvc.perform(put("/user/{id}", expected.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)
                        ))
                .andDo(print())
                .andExpectAll(
                        status().isAccepted(),
                        jsonPath("$.userId").exists(),
                        jsonPath("$.userId").value(expected.getUserId()),
                        jsonPath("$.name").value(expected.getName())
                );
    }

    @Test
    void updatePartUserTest() throws Exception {
        UserDto expected = userDto1;

        when(userServiceJpa.updatePartUser(expected.getUserId(), expected)).thenReturn(expected);

        mockMvc.perform(patch("/user/{id}", expected.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)
                        ))
                .andDo(print())
                .andExpectAll(
                        status().isAccepted(),
                        jsonPath("$.userId").exists(),
                        jsonPath("$.userId").value(expected.getUserId()),
                        jsonPath("$.name").value(expected.getName())
                );
    }

    @Test
    void updatePhoneNumberTest() throws Exception {
        UserLimitedDto expected = userLimitedDto1;
        String phoneExpected = userLimitedDto1.getPhoneNumber();

        when(userServiceJpa.updatePhoneNumber(expected.getUserId(),phoneExpected)).thenReturn(expected);

        mockMvc.perform(patch("/user/phone/{id}", expected.getUserId())
                        .param("phone",phoneExpected))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.phoneNumber").exists(),
                        jsonPath("$.phoneNumber").value(expected.getPhoneNumber())
                );
    }

    @Test
    void deleteUserTest() {
    }

    @Test
    void handlerIllegalArgumentExceptionTest() throws Exception {

        Long idExpected = 5L;

        when(userServiceJpa.getUserById(idExpected)).thenThrow(new NoSuchElementException("User with id = "+ idExpected +"is not found."));

        mockMvc.perform(get("/user/{id}", idExpected))
                .andDo(print())
                .andExpectAll(
                        status().isNotFound()
                );
    }
}