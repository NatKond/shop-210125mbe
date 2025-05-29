package de.telran.shop210125mbe.model.dto;

import lombok.*;

import java.text.DecimalFormat;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class OrderItemDto {

    private Long orderItemId;

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private DecimalFormat priceAtPurchase;

}
