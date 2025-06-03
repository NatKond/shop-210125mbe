package de.telran.shop210125mbe.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
}
