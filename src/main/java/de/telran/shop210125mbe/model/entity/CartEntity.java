package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "Cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private Long cartId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID", referencedColumnName = "userId")
    private UserEntity user;

    @OneToMany(mappedBy = "cart")
    private Set<CartItemEntity> cartItems = new HashSet<>();
}
