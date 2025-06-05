package de.telran.shop210125mbe.model.dto;
import de.telran.shop210125mbe.model.entity.CartEntity;
import de.telran.shop210125mbe.model.entity.FavoriteEntity;
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

    private String name;

    private String email;

    private String phoneNumber;

    private String passwordHash;

    private String role;

//    private Long cartId;

    private CartDto cart;

    private Set<FavoriteDto> favorites;

}
