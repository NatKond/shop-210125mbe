package de.telran.shop210125mbe.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class CategoryDto {

    private Long categoryId;

    private String name;

}
