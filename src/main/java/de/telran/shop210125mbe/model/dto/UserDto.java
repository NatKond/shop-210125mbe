package de.telran.shop210125mbe.model.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UserDto {

    private Long userId;

    private String name;

    private String email;

    private String phoneNumber;

    private String passwordHash;

    private String role;

}
