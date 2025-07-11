package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = {"products"})
@EqualsAndHashCode(exclude = {"products"})
@Builder
@Entity
@Table(name = "Categories")
public class CategoryEntity {

    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products = new HashSet<>();
}
