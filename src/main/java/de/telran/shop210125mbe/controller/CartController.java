package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.Cart;
import de.telran.shop210125mbe.service.CartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    CartServiceInterface cartServiceInterface;

    @GetMapping
    public List<Cart> getAllCarts(){
        System.out.println("Get all carts");
        return cartServiceInterface.getAllCarts();
    }

    @PostMapping
    public void insertCart(){
        System.out.println("Insert Cart");
    }

    @PutMapping
    public void updateCart(){
        System.out.println("Update cart");
    }

    @DeleteMapping
    public void deleteCart(){
        System.out.println("Delete cart");
    }
}
