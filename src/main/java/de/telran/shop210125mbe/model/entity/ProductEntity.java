package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

//@Data
//@Entity
//@Table(name = "Product")
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

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CategoryID") // имя колонки для связи с CategoriesEntity
    private CategoryEntity category;

}
