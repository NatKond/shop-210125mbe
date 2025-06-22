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
public class CategoryDto {

    private Long categoryId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
}
