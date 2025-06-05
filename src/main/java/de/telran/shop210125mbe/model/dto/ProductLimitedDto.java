package de.telran.shop210125mbe.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ProductLimitedDto {
    private Long productId;

    private String name;

//    private String description;
//
//    private Double price;
}
