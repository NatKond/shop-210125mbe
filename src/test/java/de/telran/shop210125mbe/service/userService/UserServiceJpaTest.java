package de.telran.shop210125mbe.service.userService;

import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserLimitedDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@FieldDefaults(level = AccessLevel.PRIVATE) // присваивает область видимости по умолчанию для переменных класса
@ExtendWith(MockitoExtension.class)
class UserServiceJpaTest {

    @Mock // объект, поведение которого мы будем имитировать
    private UserRepository userRepositoryMock;

    @Mock
    private Mappers mappersMock;

    @InjectMocks
    private UserServiceJpa userServiceJpa; // класс, но может быть и интерфейс

    UserEntity userEntity1;
    UserEntity userEntity2;

    @BeforeEach
    void setUp() {
        userEntity1 = UserEntity.builder()
                .userId(1L)
                .name("TestUser1")
                .email("test1@i.com")
                .phoneNumber("+49111222222222")
                .build();
        userEntity2 = UserEntity.builder()
                .userId(2L)
                .name("TestUser2")
                .email("test2@i.com")
                .phoneNumber("+49111222222222")
                .build();
    }

    @Test
    void initTest(){
        // given
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(new UserEntity());

        userServiceJpa.init();

        verify(userRepositoryMock, times(3)).save(any(UserEntity.class));

    }


    @Test
    void getAllUsersTest() {
        // given
        UserLimitedDto userLimitedDtoExpected1 = UserLimitedDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .build();
        UserLimitedDto userLimitedDtoExpected2 = UserLimitedDto.builder()
                .userId(userEntity2.getUserId())
                .name(userEntity2.getName())
                .build();

        List<UserLimitedDto> userLimitedDtoListExpected = List.of(userLimitedDtoExpected1, userLimitedDtoExpected2);

        when(userRepositoryMock.findAll()).thenReturn(List.of(userEntity1, userEntity2));
        when(mappersMock.convertToUserLimitedDto(userEntity1)).thenReturn(userLimitedDtoExpected1);
        when(mappersMock.convertToUserLimitedDto(userEntity2)).thenReturn(userLimitedDtoExpected2);

        //when
        List<UserLimitedDto> userLimitedDtoListActual = userServiceJpa.getAllUsers();

        // then
        assertNotNull(userLimitedDtoListActual);
        assertEquals(userLimitedDtoListExpected, userLimitedDtoListActual);
        verify(userRepositoryMock).findAll();
        verify(mappersMock, times(2)).convertToUserLimitedDto(any(UserEntity.class));
    }

    @Test
    void getUserByIdTest() {
        // given
        UserEntity userEntityExpected = userEntity1;

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntityExpected.getUserId())
                .name(userEntityExpected.getName())
                .email(userEntityExpected.getEmail())
                .phoneNumber(userEntityExpected.getPhoneNumber())
                .build();

        Long idExpected = 1L;

        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(userEntityExpected));
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);

        // when
        UserDto userDtoActual = userServiceJpa.getUserById(idExpected);

        // then
        assertNotNull(userDtoActual);
        assertEquals(userEntityExpected.getUserId(), userDtoActual.getUserId());
        assertEquals(userDtoExpected, userDtoActual);
        verify(userRepositoryMock).findById(idExpected); // был ли запущем метод findById
        verify(mappersMock, times(1)).convertToUserDto(any(UserEntity.class)); // запущен ли этот метод 1 раз
    }

    @Test
    void getByEmailTest() {
    }

    @Test
    void getByNameTest() {
    }

    @Test
    void getByNameAndEmailTest() {
    }

    @Test
    void getByPhoneTest() {
    }

    @Test
    void createUserTest() {
    }

    @Test
    void updateUserTest() {
    }

    @Test
    void updatePartUseTest() {
    }

    @Test
    void updatePhoneNumberTest() {
    }

    @Test
    void deleteUserByIdTest() {
    }
}