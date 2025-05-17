package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.CartItem;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public CartItem getCartItemById(Long id) {
        return localeStorage.stream().filter(cartItem -> cartItem.getCartItemId().equals(id))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Cart item with id = " + id + " is not found."));
    }

    @Override
    public CartItem createCartItem(CartItem newCartItem) {
        if (newCartItem.getCartItemId() != null &&
                localeStorage.stream()
                        .noneMatch(cartItem -> cartItem.getCartItemId().equals(newCartItem.getCartItemId()))) {
            localeStorage.add(newCartItem);
            return getCartItemById(newCartItem.getCartItemId());
        }
        throw new RuntimeException("Cart item is not created.");
    }

    @Override
    public CartItem updateCartItem(Long id, CartItem updatedCartItem) {
        for (int i = 0; i < localeStorage.size(); i++) {
            if (localeStorage.get(i).getCartItemId().equals(id)) {
                localeStorage.set(i, updatedCartItem);
                return localeStorage.get(i);
            }
        }
        return createCartItem(updatedCartItem);
    }

    @Override
    public CartItem updatePartCartItem(Long id, CartItem updatedCartItem) {
        for (CartItem cartItem : localeStorage) {
            if (cartItem.getCartItemId().equals(id)) {
                if (updatedCartItem.getCartItemId() != null &&
                        !updatedCartItem.getCartItemId().equals(cartItem.getCartItemId())) {
                    cartItem.setCartItemId(updatedCartItem.getCartItemId());
                }
                if (updatedCartItem.getCartId() != null &&
                        !updatedCartItem.getCartId().equals(cartItem.getCartId())) {
                    cartItem.setCartId(updatedCartItem.getCartId());
                }
                if (updatedCartItem.getProductId() != null &&
                        !updatedCartItem.getProductId().equals(cartItem.getProductId())) {
                    cartItem.setProductId(updatedCartItem.getProductId());
                }
                if (updatedCartItem.getQuantity() != null &&
                        !updatedCartItem.getQuantity().equals(cartItem.getQuantity())) {
                    cartItem.setQuantity(updatedCartItem.getQuantity());
                }
                return cartItem;
            }
        }
        throw new IllegalArgumentException("Cart item id = " + id + " is not found.");
    }

    @Override
    public void deleteCartItemById(Long id) {
        if (localeStorage.removeIf(cartItem -> cartItem.getCartItemId().equals(id))) return;
        throw new IllegalArgumentException("Cart item id = " + id + " is not found.");
    }
}
