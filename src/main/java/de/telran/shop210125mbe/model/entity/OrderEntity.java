package de.telran.shop210125mbe.model.entity;

import de.telran.shop210125mbe.pojo.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Orders")
public class OrderEntity {

    @Id
    @Column(name = "OrderID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "DeliveryAddress")
    private String deliveryAddress;

    @Column(name = "ContactPhone")
    private String contactPhone;

    @Column(name = "DeliveryMethod")
    private String deliveryMethod;

    @Column(name = "Status")
    private Status status;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "order")
    private Set<OrderItemEntity> orderItems = new HashSet<>();
}
