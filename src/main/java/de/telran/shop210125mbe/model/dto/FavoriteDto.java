package de.telran.shop210125mbe.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class FavoriteDto {
    private Long favoriteId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;

    private ProductLimitedDto product;
    //private Long productId;

}
