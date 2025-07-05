package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserLimitedDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Tag(
        name = "Users", description = "Контроллер для работы с пользователями",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по категориям",
                url = "https://confirmed-baron-2e5.notion.site/REST-API-f186cf63a46c4020b2237f73093922ab"
        )
)
public interface UserControllerInt {

    @Operation(
            summary = "Все клиенты",
            description = "Позволяет получить информацию о всех клиентах"
    )
    List<UserLimitedDto> getAllUsers();


    @Operation(
            summary = "Клиент с соотвествующем id",
            description = "Позволяет получить информацию об одном клиенте"
    )
    UserDto getUserById(
            @Parameter(description = "Get information by Id", required = true, example = "1")
            Long id);

    @Hidden
    UserDto getUserByEmail(String email);

    @Hidden
    List<UserDto> getUserByName(String name);

    UserDto createUser(
            @Parameter(description = "New User json", required = true,
                    example = """
                                {
                                    "name": "Eva Green",
                                    "email": "eva.green@example.com",
                                    "phoneNumber": "+1555666777",
                                    "passwordHash": "hashed_password_5",
                                    "role": "ADMINISTRATOR"
                                }
                            """)
            UserDto userDto);

    UserDto updateUser(Long id, UserDto updatedUserDto);

    void deleteUser(Long id);

}
