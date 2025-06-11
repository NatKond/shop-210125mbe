package de.telran.shop210125mbe.service.userService;

import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.repository.CartRepository;
import de.telran.shop210125mbe.repository.FavoriteRepository;
import de.telran.shop210125mbe.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceJpaTest {

    @Mock // объект, поведение которого мы будем имитировать
    private UserRepository userRepositoryMock;

//    @Mock
//    private FavoriteRepository favoriteRepositoryMock;
//
//    @Mock
//    private CartRepository cartRepositoryMock;

    @Mock
    private Mappers mappersMock;

    @InjectMocks
    private UserServiceJpa userServiceJpa; // прямой класс, но может быть и интерфейс

    @Test
    void getAllUsersTest() {

    }

    @Test
    void getUserByIdTest() {
        // given
        UserEntity userEntityExpected = UserEntity.builder()
                .userId(1L)
                .name("TestUser")
                .email("test@i.com")
                .phoneNumber("+49111222222222")
                .build();

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