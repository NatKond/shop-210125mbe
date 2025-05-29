package de.telran.shop210125mbe.service.userService;

import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserLimitedDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.pojo.Role;
import de.telran.shop210125mbe.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor // будет создан конструктор, аргументы которого будут переменные
@Service
public class UserServiceJpa{

    // @Autowired
    private final UserRepository userRepository;

//    @Autowired // DI через конструктор (в SpringBoot 3.0 эту аннотацию выполнять не обязательно)
//    public UserServiceJpa(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @Autowired // DI через сеттер (в SpringBoot 3.0 эту аннотацию выполнять не обязательно)
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostConstruct
    void init(){
        UserEntity userEntity1 = UserEntity.builder()
                .name("Alice Johnson")
                .email("alice.johnson@example.com")
                .phoneNumber("+1234567890")
                .passwordHash("hashed_password_1")
                .role(Role.CLIENT)
                .build();

        userRepository.save(userEntity1);

        UserEntity userEntity2 = UserEntity.builder()
                .name("Bob Smith")
                .email("bob.smith@example.com")
                .phoneNumber("+1987654321")
                .passwordHash("hashed_password_2")
                .role(Role.ADMINISTRATOR)
                .build();

        userRepository.save(userEntity2);

        UserEntity userEntity3 = UserEntity.builder()
                .name("Carol Lee")
                .email("carol.lee@example.com")
                .phoneNumber("+1122334455")
                .passwordHash("hashed_password_3")
                .role(Role.CLIENT)
                .build();

        userRepository.save(userEntity3);
    }

    public List<UserLimitedDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userEntity -> UserLimitedDto.builder()
                        .userId(userEntity.getUserId())
                        .name(userEntity.getName())
                        .email(userEntity.getEmail())
                        .phoneNumber(userEntity.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id = " + id + " is not found."));

        return UserDto.builder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .passwordHash("*********")
                .role(userEntity.getRole().name())
                .build();
    }

    public UserDto getByEmail(String valueEmail) {
        //Вызываем самостоятельно описанный метод из репозитория
        UserEntity returnUserEntity = userRepository.findByEmailNativeQuery(valueEmail).orElseThrow(() -> new NoSuchElementException("User with email = " + valueEmail + " is not found."));

        //Трансформируем в Dto
        UserDto returnUserDto = UserDto.builder()
                .userId(returnUserEntity.getUserId())
                .email(returnUserEntity.getEmail())
                .name(returnUserEntity.getName())
                .role(returnUserEntity.getRole().toString())
                .phoneNumber(returnUserEntity.getPhoneNumber())
                .passwordHash("******")
                .build();

        return returnUserDto;
    }

    public List<UserDto> getByName(String valueName) {
        //Вызываем самостоятельно описанный метод из репозитория
        List<UserEntity> returnUsersEntity = userRepository.findByNameHql(valueName);

        //Трансформируем в List Dto
        List<UserDto> returnUsersDto =
                returnUsersEntity.stream()
                        .map(userEntity ->
                                UserDto.builder()
                                        .userId(userEntity.getUserId())
                                        .email(userEntity.getEmail())
                                        .name(userEntity.getName())
                                        .role(userEntity.getRole().toString())
                                        .phoneNumber(userEntity.getPhoneNumber())
                                        .passwordHash("******")
                                        .build())
                        .collect(Collectors.toList());

        return returnUsersDto;
    }

    public UserDto createUser(UserDto newUserDto) {
        if (newUserDto.getUserId() != null){
            throw new IllegalArgumentException("UserID should not be defined.");
        }

        UserEntity userEntity = UserEntity.builder()
                .name(newUserDto.getName())
                .email(newUserDto.getEmail())
                .phoneNumber(newUserDto.getPhoneNumber())
                .passwordHash(newUserDto.getPasswordHash())
                .role(Role.valueOf(newUserDto.getRole()))
                .build();

        userEntity = userRepository.save(userEntity);

        return getUserById(userEntity.getUserId());
    }

    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        if (updatedUserDto.getUserId() != null){
            throw new IllegalArgumentException("UserID should not be defined.");
        }
        UserEntity userEntity = userRepository.findById(id).orElse(new UserEntity());
        userEntity.setName(updatedUserDto.getName());
        userEntity.setEmail(updatedUserDto.getEmail());
        userEntity.setPhoneNumber(updatedUserDto.getPhoneNumber());
        userEntity.setPasswordHash(updatedUserDto.getPasswordHash());
        userEntity.setRole(Role.valueOf(updatedUserDto.getRole()));
        userRepository.save(userEntity);
        return getUserById(id);
    }

    public UserDto updatePartUser(Long id, UserDto updatedUserDto) {
        UserEntity existingUserEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id = " + id + " is not found."));

        if (updatedUserDto.getName() != null &&
                !updatedUserDto.getName().equals(existingUserEntity.getName())) {
            existingUserEntity.setName(updatedUserDto.getName());
        }
        if (updatedUserDto.getEmail() != null &&
                !updatedUserDto.getEmail().equals(existingUserEntity.getEmail())) {
            existingUserEntity.setEmail(updatedUserDto.getEmail());
        }
        if (updatedUserDto.getRole() != null &&
                !updatedUserDto.getRole().equals(existingUserEntity.getRole().name())) {
            existingUserEntity.setRole(Role.valueOf(updatedUserDto.getRole()));
        }
        if (updatedUserDto.getPhoneNumber() != null &&
                !updatedUserDto.getPhoneNumber().equals(existingUserEntity.getPhoneNumber())) {
            existingUserEntity.setPhoneNumber(updatedUserDto.getPhoneNumber());
        }
        userRepository.save(existingUserEntity);
        return getUserById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserLimitedDto updatePhoneNumber(Long id, String phoneNumber) {
        if (userRepository.setPhoneNumber(id, phoneNumber) < 0){
         throw new NoSuchElementException("User with id = " + id + " is not found.");
        }
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id = " + id + " is not found."));
        return UserLimitedDto.builder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }

    public List<UserLimitedDto> getByNameAndEmail(String name, String valueEmail) {
        List<UserEntity> userEntities = userRepository.findByNameContainingAndEmail(name, valueEmail);
        return userEntities.stream()
                .map(userEntity -> UserLimitedDto.builder()
                        .userId(userEntity.getUserId())
                        .email(userEntity.getEmail())
                        .name(userEntity.getName())
                        .phoneNumber(userEntity.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    public UserDto getByPhone(String valuePhoneNumber) {
        UserEntity userEntity = userRepository.findByPhoneNumber(valuePhoneNumber).orElseThrow(() -> new NoSuchElementException("User with phone number = " + valuePhoneNumber + " is not found."));
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .role(userEntity.getRole().toString())
                .phoneNumber(userEntity.getPhoneNumber())
                .passwordHash("******")
                .build();
    }
}
