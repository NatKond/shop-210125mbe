package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.Order;
import de.telran.shop210125mbe.service.orderService.OrderServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.*;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {
    //    @Autowired
    private final OrderServiceInterface orderServiceInterface;

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
        System.out.println(YELLOW + "Get " + id + " order" + RESET);
        Order order = orderServiceInterface.getOrderById(id);
        return (order != null) ? ResponseEntity.ok(order) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with id = " + id + " is not found.");
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Order newOrder) {
        System.out.println(YELLOW + "Create order" + RESET);
        Order order = orderServiceInterface.createOrder(newOrder);
        return order != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(order) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        System.out.println(YELLOW + "Update " + id + " order" + RESET);
        Order order = orderServiceInterface.udateOrder(id, updatedOrder);
        return order != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(order) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        System.out.println(YELLOW + "Update order " + id + " partially" + RESET);
        Order order = orderServiceInterface.udatePartOrder(id, updatedOrder);
        return order != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(order) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        System.out.println(YELLOW + "Delete " + id + " order" + RESET);
        return (orderServiceInterface.deleteOrderById(id)) ? ResponseEntity.ok("Order with id = " + id + " is deleted.") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with id = " + id + " is not found.");
    }
}
