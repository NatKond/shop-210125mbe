package de.telran.shop210125mbe.model.dto;

import de.telran.shop210125mbe.model.entity.UserEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class CartDto {

    private Long cartId;

    private Long userId;
}
