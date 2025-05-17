package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Cart;

import java.util.List;

public interface CartDbInterface {

    Cart save(Cart cart);

    Cart findById(Long id);

    List<Cart> findAll();

    Cart update(Long id, Cart cart);

    boolean delete(long id);

}
