package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Cart;

import java.util.List;

public interface CartServiceInterface {

    List<Cart> getAllCarts();

    Cart getCartById(Long id);

    Cart createCart(Cart newCart);

    Cart updateCart(Long id, Cart updatedCart);

    Cart updatePartCart(Long id, Cart updatedCart);

    Boolean deleteCartById(Long id);

}
