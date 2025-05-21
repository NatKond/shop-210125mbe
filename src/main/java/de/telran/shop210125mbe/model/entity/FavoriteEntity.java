package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "Favorites")
public class FavoriteEntity {

    @Id
    @Column(name = "FavoriteID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity product;
}
