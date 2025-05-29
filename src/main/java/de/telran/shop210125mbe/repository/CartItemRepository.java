package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
