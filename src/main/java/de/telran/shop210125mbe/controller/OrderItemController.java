package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.OrderItem;
import de.telran.shop210125mbe.service.orderItemService.OrderItemServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;

@RestController
@RequestMapping(value = "/orderItem")
@RequiredArgsConstructor
public class OrderItemController {

    //    @Autowired
    private final OrderItemServiceInterface orderItemServiceInterface;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        System.out.println(YELLOW + "Get all order items" + RESET);
        return orderItemServiceInterface.getAllOrderItems();
    }

    @PostMapping
    public void insertOrderItem() {
        System.out.println(YELLOW + "Insert order item" + RESET);
    }

    @PutMapping
    public void updateOrderItem() {
        System.out.println(YELLOW + "Update order item" + RESET);
    }

    @DeleteMapping
    public void deleteOrderItem() {
        System.out.println(YELLOW + "Delete order item" + RESET);
    }
}
