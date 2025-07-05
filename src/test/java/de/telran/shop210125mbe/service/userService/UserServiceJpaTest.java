package de.telran.shop210125mbe.service.userService;

import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserLimitedDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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
    UserRepository userRepositoryMock;

    @Mock
    Mappers mappersMock;

    @InjectMocks
    UserServiceJpa userServiceJpa; // класс, но может быть и интерфейс

    static UserEntity userEntity1;

    static UserEntity userEntity2;

    @BeforeAll
    static void setUp() {
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

    @DisplayName("Test method init")
    @Test
    void initTest() {
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(new UserEntity());

        userServiceJpa.init();

        verify(userRepositoryMock, times(3)).save(any(UserEntity.class));
    }

    @DisplayName("Test method getAllUsers")
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

        //then
        assertNotNull(userLimitedDtoListActual);
        assertEquals(userLimitedDtoListExpected, userLimitedDtoListActual);
        verify(userRepositoryMock).findAll(); // был ли запущен этот метод
        verify(mappersMock, times(2)).convertToUserLimitedDto(any(UserEntity.class));  // был ли этот метод запущен 2 раза
    }

    @DisplayName("Test method getUserById")
    @Test
    void getUserByIdTest() {
        // given
        UserEntity userEntityExpected = userEntity1;

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .build();

        Long idExpected = userEntity1.getUserId();

        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(userEntityExpected));
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);

        // when
        UserDto userDtoActual = userServiceJpa.getUserById(idExpected);

        // then
        assertNotNull(userDtoActual);
        assertEquals(userEntityExpected.getUserId(), userDtoActual.getUserId());
        assertEquals(userDtoExpected, userDtoActual);
        verify(userRepositoryMock).findById(idExpected); // был ли запущем метод findById
        verify(mappersMock).convertToUserDto(any(UserEntity.class)); // запущен ли этот метод 1 раз
    }

    @DisplayName("Test method getByEmail")
    @Test
    void getByEmailTest() {
        // given
        UserEntity userEntityExpected = userEntity1;

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .build();

        String emailExpected = userEntity1.getEmail();

        when(userRepositoryMock.findByEmailNativeQuery(anyString())).thenReturn(Optional.of(userEntityExpected));
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);

        // when
        UserDto userDtoActual = userServiceJpa.getByEmail(emailExpected);

        // then
        assertNotNull(userDtoActual);
        assertEquals(userEntityExpected.getEmail(), userDtoActual.getEmail());
        assertEquals(userDtoExpected, userDtoActual);
        verify(userRepositoryMock).findByEmailNativeQuery(emailExpected);
        verify(mappersMock).convertToUserDto(any(UserEntity.class));
    }

    @DisplayName("Test method getByName")
    @Test
    void getByNameTest() {
        // given
        UserEntity userEntityExpected = userEntity1;

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .build();

        List<UserDto> userDtoListExpected = List.of(userDtoExpected);

        String nameExpected = userEntity1.getName();

        when(userRepositoryMock.findByNameHql(anyString())).thenReturn(List.of(userEntityExpected));
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);

        // when
        List<UserDto> userDtoListActual = userServiceJpa.getByName(nameExpected);

        // then
        assertNotNull(userDtoListActual);
        assertEquals(userEntityExpected.getName(), userDtoListActual.getFirst().getName());
        assertEquals(userDtoListExpected, userDtoListActual);
        verify(userRepositoryMock).findByNameHql(nameExpected);
        verify(mappersMock).convertToUserDto(any(UserEntity.class));
    }

    @DisplayName("Test method getByPhone")
    @Test
    void getByPhoneTest() {
        // given
        UserEntity userEntityExpected = userEntity1;

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .build();

        String phoneExpected = userEntity1.getPhoneNumber();

        when(userRepositoryMock.findByPhoneNumber(anyString())).thenReturn(Optional.of(userEntityExpected));
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);

        // when
        UserDto userDtoActual = userServiceJpa.getByPhone(phoneExpected);

        // then
        assertNotNull(userDtoActual);
        assertEquals(userEntityExpected.getPhoneNumber(), userDtoActual.getPhoneNumber());
        assertEquals(userDtoExpected, userDtoActual);
        verify(userRepositoryMock).findByPhoneNumber(phoneExpected);
        verify(mappersMock).convertToUserDto(any(UserEntity.class));
    }

    @DisplayName("Test method createUser")
    @Test
    void createUserTest() {
        // given
        UserEntity userEntityExpected = userEntity1;

        UserEntity newUserEntity = UserEntity.builder()
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .build();

        UserDto newUserDto = UserDto.builder()
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .build();

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .build();

        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(userEntityExpected);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(userEntityExpected));
        when(mappersMock.convertToUserEntity(newUserDto)).thenReturn(newUserEntity);
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);

        // when
        UserDto userDtoActual = userServiceJpa.createUser(newUserDto);

        // then
        assertNotNull(userDtoActual);
        assertEquals(userDtoExpected, userDtoActual);
        verify(userRepositoryMock).save(newUserEntity);
        verify(userRepositoryMock).findById(anyLong());
        verify(mappersMock).convertToUserDto(any(UserEntity.class));
        verify(mappersMock).convertToUserEntity(any(UserDto.class));
    }

    @DisplayName("Test method updateUser")
    @Test
    void updateUserTest() {
        // given
        UserEntity userEntityExpected = userEntity1;

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(userEntity1.getPhoneNumber())
                .build();

        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(userEntityExpected));
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(userEntityExpected);
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);
        when(mappersMock.convertToUserEntity(userDtoExpected)).thenReturn(userEntityExpected);

        // when
        UserDto userDtoActual = userServiceJpa.updateUser(userDtoExpected.getUserId(), userDtoExpected);

        // then
        assertNotNull(userDtoActual);
        assertEquals(userDtoExpected, userDtoActual);
        verify(userRepositoryMock).save(userEntityExpected);
        verify(mappersMock).convertToUserDto(any(UserEntity.class));
        verify(mappersMock).convertToUserEntity(any(UserDto.class));

    }

    @DisplayName("Test method updatePartUser")
    @Test
    void updatePartUserTest() {
        // given
        UserEntity existingUserEntity = userEntity1;

        UserDto updatedUserDto = UserDto.builder()
                .phoneNumber("+49111333333333")
                .build();

        UserEntity userEntityExpected = UserEntity.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(updatedUserDto.getPhoneNumber())
                .build();

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(updatedUserDto.getPhoneNumber())
                .build();

        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(existingUserEntity));
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(userEntityExpected);
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);

        // when
        UserDto userDtoActual = userServiceJpa.updatePartUser(userDtoExpected.getUserId(), updatedUserDto);

        // then
        assertNotNull(userDtoActual);
        assertEquals(userDtoExpected, userDtoActual);
        verify(userRepositoryMock).findById(userDtoExpected.getUserId());
        verify(userRepositoryMock).save(userEntityExpected);
        verify(mappersMock).convertToUserDto(any(UserEntity.class));
    }

    @DisplayName("Test method updatePhoneNumber")
    @Test
    void updatePhoneNumberTest() {
        // given
        UserEntity existingUserEntity = userEntity1;
        Long idExpected = existingUserEntity.getUserId();
        String phoneExpected = "+49111333333333";

        UserEntity userEntityExpected = UserEntity.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(phoneExpected)
                .build();

        UserLimitedDto userLimitedDtoExpected = UserLimitedDto.builder()
                .userId(userEntity1.getUserId())
                .name(userEntity1.getName())
                .email(userEntity1.getEmail())
                .phoneNumber(phoneExpected)
                .build();

        when(userRepositoryMock.setPhoneNumber(anyLong(), anyString())).thenReturn(1);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(userEntityExpected));
        when(mappersMock.convertToUserLimitedDto(userEntityExpected)).thenReturn(userLimitedDtoExpected);

        // when
        UserLimitedDto userLimitedDtoActual = userServiceJpa.updatePhoneNumber(idExpected, phoneExpected);

        // then
        assertNotNull(userLimitedDtoActual);
        assertEquals(userLimitedDtoExpected, userLimitedDtoActual);
        verify(userRepositoryMock).findById(userLimitedDtoExpected.getUserId());
        verify(userRepositoryMock).setPhoneNumber(idExpected,phoneExpected);
        verify(mappersMock).convertToUserLimitedDto(any(UserEntity.class));
    }


    @DisplayName("Test method deleteUserById")
    @Test
    void deleteUserByIdTest() {
        userServiceJpa.deleteUserById(1L);
        verify(userRepositoryMock).deleteById(1L);
    }
}