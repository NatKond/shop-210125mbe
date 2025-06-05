package de.telran.shop210125mbe.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.telran.shop210125mbe.model.entity.CartItemEntity;
import de.telran.shop210125mbe.model.entity.UserEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class CartDto {

    private Long cartId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CartItemDto> cartItems = new HashSet<>();
}
