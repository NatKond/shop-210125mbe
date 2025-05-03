package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Order;
import de.telran.shop210125mbe.model.Status;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceList implements OrderServiceInterface{

    List<Order> localeStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setStatus(Status.NEW);
        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setStatus(Status.PROCESSING);
        localeStorage.addAll(List.of(order1,order2));
    }


    @Override
    public List<Order> getAllOrders() {
        return localeStorage;
    }
}
