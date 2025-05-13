package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.Cart;
import de.telran.shop210125mbe.service.CartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    CartServiceInterface cartServiceInterface;

    @GetMapping
    public ResponseEntity<?> getAllCarts() {
        System.out.println("Get all carts");
        List<Cart> carts = cartServiceInterface.getAllCarts();
        return (carts != null) ? new ResponseEntity<>(carts, HttpStatus.OK) :
                new ResponseEntity<>("Carts are not found.", HttpStatus.valueOf(404));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        System.out.println("Get " + id + " cart");
        Cart cart = cartServiceInterface.getCartById(id);
        return (cart != null) ? new ResponseEntity<>(cart, HttpStatus.OK) :
                new ResponseEntity<>("Cart with id = " + id + " is not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody Cart newCart) {
        System.out.println("Create Cart");
        Cart cart = cartServiceInterface.createCart(newCart);
        return (cart != null) ? ResponseEntity.status(HttpStatus.CREATED).body(cart) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is not created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        System.out.println("Update " + id + " cart");
        Cart cart = cartServiceInterface.createCart(cartServiceInterface.updateCart(id, updatedCart));
        return (cart != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(cart) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is not updated.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        System.out.println("Update cart" + id + "partially");
        Cart cart = cartServiceInterface.updatePartCart(id, updatedCart);
        return (cart != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(cart) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with id = " + id + " is not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        System.out.println("Delete" + id + "cart");
        return (cartServiceInterface.deleteCartById(id)) ? ResponseEntity.ok().body("Cart with id = " + id + " is deleted.") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with id = " + id + " is not found.");
    }
}
