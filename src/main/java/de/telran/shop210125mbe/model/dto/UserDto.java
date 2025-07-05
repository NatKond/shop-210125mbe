package de.telran.shop210125mbe.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UserDto {

    @Schema(description = "Unique user identification")
    @Null(groups = Marker.OnCreate.class, message = "User id should be null when creating")
    @NotNull(groups = Marker.OnUpdate.class, message = "User id should be not null when updating")
    private Long userId;

    @Schema(description = "User name")
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 30, message = "Name should be from 2 to 30 characters")
    private String name;

    @Schema(description = "User unique email")
    @Email(message = "Should be a well-formed email address")
    private String email;

    @NotBlank(message = "Phone number is required") // @NotNull + @NotEmpty + есть символы, не только пробелы
    @Pattern(regexp = "^\\+\\d{10,}$", message = "Phone number should start with + and  be at least 10 characters")
    private String phoneNumber;

    @NotBlank(message = "Password number is required")
    @Pattern(regexp = "^\\w{8,}$", message = "Password should be at least 8 characters")
    private String passwordHash;

    @Schema(description = "User role", allowableValues = {"CLIENT", "ADMIN"})
    @NotBlank(message = "Role is required")
    private String role;

//    private Long cartId;

    private CartDto cart;

    private Set<FavoriteDto> favorites;

}
