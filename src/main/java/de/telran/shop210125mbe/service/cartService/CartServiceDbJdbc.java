package de.telran.shop210125mbe.service.cartService;

import de.telran.shop210125mbe.jdbc.CartDbInterface;
import de.telran.shop210125mbe.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartJdbc")
public class CartServiceDbJdbc implements CartServiceInterface {

    @Autowired
    CartDbInterface cartDbInterface;

    @Override
    public List<Cart> getAllCarts() {
        try {
            return cartDbInterface.findAll();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public Cart getCartById(Long id) {
        try {
            return cartDbInterface.findById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Cart createCart(Cart newCart) {
        try {
            return cartDbInterface.save(newCart);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Cart updateCart(Long id, Cart updatedCart) {
        try {
            return cartDbInterface.update(id, updatedCart);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Cart updatePartCart(Long id, Cart updatedCart) {
        return null;
    }

    @Override
    public Boolean deleteCartById(Long id) {
        return cartDbInterface.delete(id);
    }
}
