package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.Order;
import de.telran.shop210125mbe.service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderServiceInterface orderServiceInterface;

    @GetMapping
    public List<Order> getAllOrders(){
        System.out.println("Get all orders");
        return orderServiceInterface.getAllOrders();
    }

    @PostMapping
    public void insertOrder(){
        System.out.println("Insert order");
    }

    @PutMapping
    public void updateOrder(){
        System.out.println("Update order");
    }

    @DeleteMapping
    public void deleteOrder() {
        System.out.println("Delete order");
    }
}
