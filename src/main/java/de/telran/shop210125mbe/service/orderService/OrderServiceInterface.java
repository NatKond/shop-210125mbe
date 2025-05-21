package de.telran.shop210125mbe.service.orderService;

import de.telran.shop210125mbe.pojo.Order;

import java.util.List;

public interface OrderServiceInterface {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(Order newOrder);

    Order udateOrder(Long id, Order updatedOrder);

    Order udatePartOrder(Long id,Order updatedOrder);

    Boolean deleteOrderById(Long id);
}
