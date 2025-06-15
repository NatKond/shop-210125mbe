package de.telran.shop210125mbe.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.pojo.Role;
import de.telran.shop210125mbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // для преобразования объекта в Json

    //    @MockitoBean
//    private UserRepository userRepository;
    @Autowired
    private UserRepository userRepository;


    UserEntity userEntity1;
    UserEntity userEntity2;
    UserEntity userEntity3;

    @BeforeEach
    void setUp() {
        // подготовка, наобходимо заполнить БД тестовыми данными, как при тестировании Repository
        userEntity1 = UserEntity.builder()
                //.userId(1L)
                .name("TestUser1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .passwordHash("test_hashed_password_1")
                .role(Role.CLIENT)
                .build();
        userEntity1 = userRepository.save(userEntity1);
        userEntity2 = UserEntity.builder()
                //.userId(2L)
                .name("TestUser2")
                .email("test2@i.com")
                .phoneNumber("+49111222222222")
                .passwordHash("test_hashed_password_2")
                .role(Role.ADMINISTRATOR)
                .build();
        userEntity2 = userRepository.save(userEntity2);
        userEntity3 = UserEntity.builder()
                //.userId(3L)
                .name("TestUser3")
                .email("test2@i.com")
                .phoneNumber("+49111222222222")
                .passwordHash("test_hashed_password_3")
                .role(Role.CLIENT)
                .build();
    }

    @AfterEach
    void tearDown(){
        userRepository.deleteById(userEntity1.getUserId());
        userRepository.deleteById(userEntity2.getUserId());
    }

    @Test
    void getAllUsersIntegrationTest() throws Exception {
        //when(userRepository.findAll()).thenReturn(List.of(userEntity1, userEntity2, userEntity3));

        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[*].userId", hasItems(
                                userEntity1.getUserId().intValue(),
                                userEntity2.getUserId().intValue())),
                        jsonPath("$[*].name", hasItems(
                                userEntity1.getName(),
                                userEntity2.getName())));
        //verify(userRepository).findAll();
    }

    @Test
    void getUserByIdIntegrationTest() throws Exception {
        //Long idExpected = userEntity1.getUserId();

        Long idExpected = userEntity1.getUserId();

        //when(userRepository.findById(idExpected)).thenReturn(Optional.of(userEntity1));

        mockMvc.perform(get("/user/" + idExpected))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.userId").value(userEntity1.getUserId()),
                        jsonPath("$.name").value(userEntity1.getName())
                );

        //verify(userRepository, atLeastOnce()).findById(idExpected);
    }

    @Test
    void createPartSystemUserTest() throws Exception {
        //when(userRepository.save(any())).thenReturn(userEntity1);
        //when(userRepository.findById(any())).thenReturn(Optional.of(userEntity1));

        UserDto userDtoInput = UserDto.builder()
                .name(userEntity3.getName())
                .email(userEntity3.getEmail())
                .phoneNumber(userEntity3.getPhoneNumber())
                .passwordHash(userEntity3.getPasswordHash())
                .role(userEntity3.getRole().toString())
                .build();

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDtoInput)))
                .andDo(print())
                .andExpectAll(status().isCreated())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.name").value(userDtoInput.getName()));
    }
}
