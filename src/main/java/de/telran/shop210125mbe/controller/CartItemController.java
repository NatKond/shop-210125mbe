package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.CartItem;
import de.telran.shop210125mbe.service.CartItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cartItem")
public class CartItemController {

    @Autowired
    CartItemServiceInterface cartItemServiceInterface;

    @GetMapping
    public List<CartItem> getAllCartItems() {
        System.out.println("Get all cartItems");
        return cartItemServiceInterface.getAllCartItems();
    }

    @PostMapping
    public void insertCartItem(){
        System.out.println("Insert cart item");
    }

    @PutMapping
    public void updateCartItem(){
        System.out.println("Update cart item");
    }

    @DeleteMapping
    public void deleteCartItem(){
        System.out.println("Delete cart item");
    }
}
