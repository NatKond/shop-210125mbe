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

    private Long cartItemId;

    private Long cartId;

    private ProductLimitedDto product;

    // private Long productId;

    private Integer quantity;

}
