package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Order;
import de.telran.shop210125mbe.model.Status;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceList implements OrderServiceInterface {

    List<Order> localeStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setStatus(Status.NEW);
        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setStatus(Status.PROCESSING);
        localeStorage.addAll(List.of(order1, order2));
    }


    @Override
    public List<Order> getAllOrders() {
        return localeStorage;
    }

    @Override
    public Order getOrderById(Long id) {
        return localeStorage.stream().filter(order -> order.getOrderId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Order createOrder(Order newOrder) {
        if (newOrder.getOrderId() != null &&
                localeStorage.stream()
                        .noneMatch(order -> order.getOrderId().equals(newOrder.getOrderId()))) {
            localeStorage.add(newOrder);
            return getOrderById(newOrder.getOrderId());
        }
        return null;
    }

    @Override
    public Order udateOrder(Long id, Order updatedOrder) {
        if (updatedOrder.getOrderId() == null) return null;
        for (int i = 0; i < localeStorage.size(); i++) {
            if (localeStorage.get(i).getOrderId().equals(id)) {
                localeStorage.set(i, updatedOrder);
                return localeStorage.get(i);
            }
        }
        return createOrder(updatedOrder);
    }

    @Override
    public Order udatePartOrder(Long id, Order updatedOrder) {
        for (Order order : localeStorage) {
            if (order.getOrderId().equals(id)) {
                if (updatedOrder.getOrderId() != null &&
                        !updatedOrder.getOrderId().equals(order.getOrderId())) {
                    order.setOrderId(updatedOrder.getOrderId());
                }
                if (updatedOrder.getStatus() != null &&
                        !updatedOrder.getStatus().equals(order.getStatus())) {
                    order.setStatus(updatedOrder.getStatus());
                }
                if (updatedOrder.getContactPhone() != null &&
                        !updatedOrder.getContactPhone().equals(order.getContactPhone())) {
                    order.setContactPhone(updatedOrder.getContactPhone());
                }
                if (updatedOrder.getDeliveryAddress() != null &&
                        !updatedOrder.getDeliveryAddress().equals(order.getDeliveryAddress())) {
                    order.setDeliveryAddress(updatedOrder.getDeliveryAddress());
                }
                if (updatedOrder.getDeliveryMethod() != null &&
                        !updatedOrder.getDeliveryMethod().equals(order.getDeliveryMethod())) {
                    order.setDeliveryMethod(updatedOrder.getDeliveryMethod());
                }
                return order;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteOrderById(Long id) {
        return localeStorage.removeIf(order -> order.getOrderId().equals(id));
    }
}
