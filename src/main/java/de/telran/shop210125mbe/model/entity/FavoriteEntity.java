package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode (exclude = {"user", "product"})
@ToString (exclude = {"user", "product"})
@Builder
@Entity
@Table(name = "Favorites")
public class FavoriteEntity {

    @Id
    @Column(name = "FavoriteID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long favoriteId;

//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity user;

//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity product;
}
