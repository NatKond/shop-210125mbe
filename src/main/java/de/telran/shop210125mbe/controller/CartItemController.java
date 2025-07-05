package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.CartItemDto;
import de.telran.shop210125mbe.pojo.CartItem;
import de.telran.shop210125mbe.service.cartItemService.CartItemServiceInterface;
import de.telran.shop210125mbe.service.cartItemService.CartItemServiceJpa;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.*;
import static java.awt.Color.YELLOW;


@RestController
@RequestMapping(value = "/cartItem")
@RequiredArgsConstructor
@Hidden
public class CartItemController {

    //    @Autowired
    private final CartItemServiceJpa cartItemServiceJpa;

    @GetMapping
    public List<CartItemDto> getAllCartItems() {
        System.out.println(YELLOW + "Get all cart items" + RESET);
        return cartItemServiceJpa.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItemDto getCartItemById(@PathVariable Long id) {
        System.out.println(YELLOW + "Get " + id + " cart item" + RESET);
        return cartItemServiceJpa.getCartItemById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CartItemDto createCartItem(@RequestBody CartItemDto newCartItem) {
        System.out.println(YELLOW + "Create cart item" + RESET);
        return cartItemServiceJpa.createCartItem(newCartItem);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public CartItemDto updateCartItem(@PathVariable Long id, @RequestBody CartItemDto updatedCartItem) {
        System.out.println(YELLOW + "Update cart " + id + " item" + RESET);
        return cartItemServiceJpa.updateCartItem(id, updatedCartItem);
    }

//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @PatchMapping("/{id}")
//    public CartItemDto updatePartCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) {
//        System.out.println(YELLOW + "Update cart " + id + " item partially" + RESET);
//        return cartItemServiceJpa.updatePartCartItem(id, updatedCartItem);
//    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        System.out.println(YELLOW + "Delete cart " + id + " item" + RESET);
        cartItemServiceJpa.deleteCartItemById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Cart item controller: " + exception.getMessage();
    }
}
