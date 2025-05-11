package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.CartItem;
import de.telran.shop210125mbe.model.Product;
import de.telran.shop210125mbe.service.CartItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cartItem")
public class CartItemController {

    @Autowired
    CartItemServiceInterface cartItemServiceInterface;

    @GetMapping
    public List<CartItem> getAllCartItems() {
        System.out.println("Get all cart items");
        return cartItemServiceInterface.getAllCartItems();
    }

    @GetMapping("find/{id}")
    public CartItem getCartItemById(@PathVariable Long id) {
        System.out.println("Get " + id + " cart item");
        return cartItemServiceInterface.getCartItemById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CartItem createCartItem(@RequestBody CartItem newCartItem) {
        System.out.println("Create cart item");
        return cartItemServiceInterface.createCartItem(newCartItem);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) {
        System.out.println("Update cart " + id + " item");
        return cartItemServiceInterface.updateCartItem(id, updatedCartItem);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public CartItem updatePartCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem){
        System.out.println("Update cart " + id + " item partially");
        return cartItemServiceInterface.updatePartCartItem(id, updatedCartItem);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        System.out.println("Delete cart " + id + " item");
        cartItemServiceInterface.deleteCartItemById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Cart item controller: " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception exception) {
        return "Sorry, an error has occurred : " + exception.getMessage() + " Please try again later.";
    }
}
