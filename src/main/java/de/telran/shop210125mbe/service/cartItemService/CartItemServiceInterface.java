package de.telran.shop210125mbe.service.cartItemService;

import de.telran.shop210125mbe.pojo.CartItem;

import java.util.List;

public interface CartItemServiceInterface {

    List<CartItem> getAllCartItems();

    CartItem getCartItemById(Long id);

    CartItem createCartItem(CartItem newCartItem);

    CartItem updateCartItem(Long id, CartItem updatedCartItem);

    CartItem updatePartCartItem(Long id, CartItem updatedCartItem);

    void deleteCartItemById(Long id);
}
