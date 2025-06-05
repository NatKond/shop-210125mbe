package de.telran.shop210125mbe.model.dto;
import lombok.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ProductDto {
    private Long productId;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Double discountPrice;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private CategoryDto category;
    // private Long categoryId;
}
