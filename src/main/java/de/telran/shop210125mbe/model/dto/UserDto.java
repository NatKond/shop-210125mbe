package de.telran.shop210125mbe.model.dto;
import de.telran.shop210125mbe.model.entity.CartEntity;
import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    private Long userId;
    @Size(min=2,max=30, message="Invalid name: Should be from 2 to 30 characters")
    private String name;

    private String email;

    @NotBlank // @NotNull + @NotEmpty + есть символы, не только пробелы
    @Pattern(regexp = "^\\+\\d{10,}$", message = "Invalid phone number")
    private String phoneNumber;

    @Pattern(regexp = "^\\w{4,}$", message = "Invalid password")
    private String passwordHash;

    private String role;

//    private Long cartId;

    private CartDto cart;

    private Set<FavoriteDto> favorites;

}
