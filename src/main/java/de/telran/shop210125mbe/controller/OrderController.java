package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.Category;
import de.telran.shop210125mbe.model.Order;
import de.telran.shop210125mbe.service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderServiceInterface orderServiceInterface;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        System.out.println("Get all orders");
        List<Order> orders = orderServiceInterface.getAllOrders();
        return (orders != null) ? ResponseEntity.ok().body(orders) :
                ResponseEntity.status(HttpStatus.valueOf(404))
                        .body("Orders are not found.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        System.out.println("Get " + id + " order");
        Order order = orderServiceInterface.getOrderById(id);
        return (order != null) ? ResponseEntity.ok(order) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with id = " + id + " is not found.");
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Order newOrder) {
        System.out.println("Create order");
        Order order = orderServiceInterface.createOrder(newOrder);
        return order != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(order) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        System.out.println("Update " + id +  " order");
        Order order = orderServiceInterface.udateOrder(id, updatedOrder);
        return order != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(order) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        System.out.println("Update order " + id + " partially");
        Order order = orderServiceInterface.udatePartOrder(id, updatedOrder);
        return order != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(order) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        System.out.println("Delete " + id + " order");
        return (orderServiceInterface.deleteOrderById(id)) ? ResponseEntity.ok("Order with id = " + id + " is deleted.") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with id = " + id + " is not found.");
    }
}
