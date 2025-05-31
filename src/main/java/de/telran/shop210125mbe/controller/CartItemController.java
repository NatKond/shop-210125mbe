package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.CartItem;
import de.telran.shop210125mbe.service.cartItemService.CartItemServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.*;
import static java.awt.Color.YELLOW;


@RestController
@RequestMapping(value = "/cartItem")
@RequiredArgsConstructor
public class CartItemController {

    //    @Autowired
    private final CartItemServiceInterface cartItemServiceInterface;

    @GetMapping
    public List<CartItem> getAllCartItems() {
        System.out.println(YELLOW + "Get all cart items" + RESET);
        return cartItemServiceInterface.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable Long id) {
        System.out.println(YELLOW + "Get " + id + " cart item" + RESET);
        return cartItemServiceInterface.getCartItemById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CartItem createCartItem(@RequestBody CartItem newCartItem) {
        System.out.println(YELLOW + "Create cart item" + RESET);
        return cartItemServiceInterface.createCartItem(newCartItem);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) {
        System.out.println(YELLOW + "Update cart " + id + " item" + RESET);
        return cartItemServiceInterface.updateCartItem(id, updatedCartItem);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public CartItem updatePartCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) {
        System.out.println(YELLOW + "Update cart " + id + " item partially" + RESET);
        return cartItemServiceInterface.updatePartCartItem(id, updatedCartItem);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        System.out.println(YELLOW + "Delete cart " + id + " item" + RESET);
        cartItemServiceInterface.deleteCartItemById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Cart item controller: " + exception.getMessage();
    }
}
