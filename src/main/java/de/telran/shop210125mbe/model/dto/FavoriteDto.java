package de.telran.shop210125mbe.model.dto;

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

    private Long userId;

    private Long productId;
}
