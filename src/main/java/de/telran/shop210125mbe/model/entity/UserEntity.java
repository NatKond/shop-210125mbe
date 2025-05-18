package de.telran.shop210125mbe.model.entity;

import de.telran.shop210125mbe.pojo.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id // эта колонка должна быть уникальной в таблице и не повторяться
    @GeneratedValue(strategy = GenerationType.IDENTITY) //БД генерирует уникальный Id
    @Column(name = "UserID")
    private Long userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "PhoneHash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private CartEntity cart;

    @OneToMany(mappedBy = "user")
    private Set<FavoriteEntity> favorites = new HashSet<>();
}
