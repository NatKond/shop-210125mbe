package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.OrderItem;
import de.telran.shop210125mbe.service.OrderItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orderItem")
public class OrderItemController {

    @Autowired
    OrderItemServiceInterface orderItemServiceInterface;

    @GetMapping
    public List<OrderItem> getAllOrderItems(){
        System.out.println("Get all order items");
        return orderItemServiceInterface.getAllOrderItems();
    }

    @PostMapping
    public void insertOrderItem(){
        System.out.println("Insert order item");
    }

    @PutMapping
    public void updateOrderItem(){
        System.out.println("Update order item");
    }

    @DeleteMapping
    public void deleteOrderItem() {
        System.out.println("Delete order item");
    }
}
