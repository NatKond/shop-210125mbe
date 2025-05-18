package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cart")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartItemID")
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "CartID")
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity product;

    @Column(name = "Quantity")
    private Integer quantity;
}
