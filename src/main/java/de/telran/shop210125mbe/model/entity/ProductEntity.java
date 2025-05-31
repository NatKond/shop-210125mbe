package de.telran.shop210125mbe.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "Products")
public class ProductEntity {

    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private Double price;

//    @Column(name = "CategoryID") // эту колонку явно создавать не надо, она создастся как элемент связи ниже
//    private Long categoryId;

    @Column(name = "ImageURL")
    private String imageUrl;

    @Column(name = "DiscountPrice")
    private Double discountPrice;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

    @ManyToOne //(cascade = {CascadeType.ALL})
    @JoinColumn(name = "CategoryID") // имя колонки для связи с CategoriesEntity
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")//, fetch = FetchType.LAZY)
    private Set<CartItemEntity> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<FavoriteEntity> favorites = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderItemEntity> orderItemEntities = new HashSet<>();
}
