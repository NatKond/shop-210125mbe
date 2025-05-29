package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
