package de.telran.shop210125mbe.model.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = {"cartItems", "favorites", "orderItemEntities"})
@EqualsAndHashCode(exclude = {"cartItems", "favorites", "orderItemEntities"})
@Builder
@Entity
@Table(name = "Products")
public class ProductEntity {

//    @PrePersist
//    protected void onCreate() {
//        createdAt = Timestamp.valueOf(LocalDateTime.now());
//        updatedAt = Timestamp.valueOf(LocalDateTime.now());
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = Timestamp.valueOf(LocalDateTime.now());
//    }

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

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false, nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "CategoryID") // имя колонки для связи с CategoriesEntity
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")//, fetch = FetchType.LAZY)
    private Set<CartItemEntity> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<FavoriteEntity> favorites = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderItemEntity> orderItemEntities = new HashSet<>();
}
