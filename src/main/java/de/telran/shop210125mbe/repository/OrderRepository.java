package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
