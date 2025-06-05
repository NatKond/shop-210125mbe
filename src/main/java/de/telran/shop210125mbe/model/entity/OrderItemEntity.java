package de.telran.shop210125mbe.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = {"order"})
@EqualsAndHashCode(exclude = {"order"})
@Builder
@Entity
@Table(name = "OrderItems")
public class OrderItemEntity {

    @Id
    @Column(name = "OrderItemID")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity product;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "PriceAtPurchase")
    private DecimalFormat priceAtPurchase;

}
