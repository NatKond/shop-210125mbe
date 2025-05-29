package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
