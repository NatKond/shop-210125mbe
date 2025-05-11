package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Order;

import java.util.List;

public interface OrderServiceInterface {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(Order newOrder);

    Order udateOrder(Long id, Order updatedOrder);

    Order udatePartOrder(Long id,Order updatedOrder);

    Boolean deleteOrderById(Long id);
}
