package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.CartItem;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemServiceList implements  CartItemServiceInterface{

    List<CartItem> localeStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        CartItem cartItem1 = new CartItem(1L,1L,2L,2);
        CartItem cartItem2 = new CartItem(2L,2L,1L,1);
        localeStorage.addAll(List.of(cartItem1,cartItem2));
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return localeStorage;
    }
}
