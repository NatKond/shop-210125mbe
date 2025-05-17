package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Cart;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceList implements CartServiceInterface {

    List<Cart> localeStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        Cart cart1 = new Cart(1L, 1L);
        Cart cart2 = new Cart(2L, 2L);

        localeStorage.addAll(List.of(cart1, cart2));
    }

    @Override
    public List<Cart> getAllCarts() {
        return localeStorage;
    }

    @Override
    public Cart getCartById(Long id) {
        return localeStorage.stream().filter(cart -> cart.getCartId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Cart createCart(Cart newCart) {
        if (newCart.getCartId() != null &&
                localeStorage.stream()
                        .noneMatch(cart -> cart.getCartId().equals(newCart.getCartId()))) {
            localeStorage.add(newCart);
            return getCartById(newCart.getCartId());
        }
        return null;
    }

    @Override
    public Cart updateCart(Long id, Cart updatedCart) {
        if (updatedCart.getCartId() == null) return null;
        for (int i = 0; i < localeStorage.size(); i++) {
            if (localeStorage.get(i).getCartId().equals(id)) {
                localeStorage.set(i, updatedCart);
                return localeStorage.get(i);
            }
        }
        return createCart(updatedCart);
    }

    @Override
    public Cart updatePartCart(Long id, Cart updatedCart) {
        for (Cart cart : localeStorage) {
            if (cart.getCartId().equals(id)) {
                if (updatedCart.getCartId() != null &&
                        !updatedCart.getCartId().equals(cart.getCartId())) {
                    cart.setCartId(updatedCart.getCartId());
                }
                if (updatedCart.getUserId() != null &&
                        !updatedCart.getUserId().equals(cart.getUserId())) {
                    cart.setUserId(updatedCart.getUserId());
                }
                return cart;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteCartById(Long id) {
        return localeStorage.removeIf(cart -> cart.getCartId().equals(id));
        //throw new IllegalArgumentException("Invalid cart index.");
    }
}
