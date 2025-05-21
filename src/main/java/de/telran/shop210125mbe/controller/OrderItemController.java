package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.OrderItem;
import de.telran.shop210125mbe.service.orderItemService.OrderItemServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orderItem")
@RequiredArgsConstructor
public class OrderItemController {

//    @Autowired
    private final OrderItemServiceInterface orderItemServiceInterface;

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
