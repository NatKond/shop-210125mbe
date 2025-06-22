package de.telran.shop210125mbe.model.dto;
import jakarta.validation.constraints.*;
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
    @Null(groups = Marker.OnCreate.class, message = "Product id should be null when creating")
    @NotNull(groups = Marker.OnUpdate.class, message = "Product id should be not null when updating")
    private Long productId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name should be from 2 to 50 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 150, message = "Description should be from 10 to 150 characters")
    private String description;

    @NotNull(message = "Price is required")
    @Positive
    private Double price;

    private String imageUrl;

    private Double discountPrice;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @NotNull(message = "Category is required")
    private CategoryDto category;
    // private Long categoryId;
}
