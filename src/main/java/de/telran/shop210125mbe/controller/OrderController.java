package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.Order;
import de.telran.shop210125mbe.service.OrderServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    OrderServiceInterface orderServiceInterface;

    @GetMapping
    public List<Order> getAllProducts(){
        System.out.println("Get all products");
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
