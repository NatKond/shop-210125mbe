package de.telran.shop210125mbe.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.pojo.Role;
import de.telran.shop210125mbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    @MockitoBean
    private UserRepository userRepository;

    static UserEntity userEntity1;
    static UserEntity userEntity2;
    static UserEntity userEntity3;

    @BeforeAll
    static void init() {
        // подготовка, наобходимо заполнить БД тестовыми данными, как при тестировании Repository
        userEntity1 = UserEntity.builder()
                .userId(1L)
                .name("TestUser1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .passwordHash("test_hashed_password_1")
                .role(Role.CLIENT)
                .build();
        userEntity2 = UserEntity.builder()
                .userId(2L)
                .name("TestUser2")
                .email("test2@i.com")
                .phoneNumber("+49111222222222")
                .passwordHash("test_hashed_password_2")
                .role(Role.ADMINISTRATOR)
                .build();
        userEntity3 = UserEntity.builder()
                .userId(3L)
                .name("TestUser3")
                .email("test2@i.com")
                .phoneNumber("+49111222222222")
                .passwordHash("test_hashed_password_3")
                .role(Role.CLIENT)
                .build();
    }

    @Test
    void getAllIntegrationTest() throws Exception {
        when(userRepository.findAll()).thenReturn(List.of(userEntity1, userEntity2, userEntity3));

        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userId").exists())
                .andExpect(jsonPath("$..name").exists());
    }

    @Test
    void createPartSystemUserTest() throws Exception{

        when(userRepository.save(any())).thenReturn(userEntity1);
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity1));

        UserDto userDtoInput = UserDto.builder()
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .passwordHash(userEntity1.getPasswordHash())
                .role(userEntity1.getRole().toString())
                .build();

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDtoInput)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$..userId").exists())
                .andExpect(jsonPath("$..name").exists());
    }
}
