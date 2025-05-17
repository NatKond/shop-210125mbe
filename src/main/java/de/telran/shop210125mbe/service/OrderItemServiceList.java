package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.OrderItem;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemServiceList implements OrderItemServiceInterface{

    List<OrderItem> localeStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrderId(1L);
        orderItem1.setQuantity(4);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrderId(2L);
        orderItem1.setQuantity(4);
        localeStorage.addAll(List.of(orderItem1,orderItem2));
    }


    @Override
    public List<OrderItem> getAllOrderItems() {
        return localeStorage;
    }
}
