package de.telran.shop210125mbe.model.dto;

import de.telran.shop210125mbe.model.entity.CartEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class CartItemDto {

    private Long cartId;

    private CartEntity cart;

    private Long productId;

    private Integer quantity;

}
