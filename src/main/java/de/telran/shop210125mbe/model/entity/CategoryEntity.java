package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

//@Data
//@Entity
//@Table(name = "Category")
public class CategoryEntity {

        @Id
        @Column(name = "CategoryID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long categoryId;

        @Column(name = "Name")
        private String name;

        @OneToMany(mappedBy = "category")
        private Set<ProductEntity> products = new HashSet<>();
}
