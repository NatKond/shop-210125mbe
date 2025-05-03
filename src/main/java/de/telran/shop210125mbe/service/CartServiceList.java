package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Cart;
import de.telran.shop210125mbe.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
}
