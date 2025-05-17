package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Product;

import java.util.List;

public interface ProductDbInterface {

    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    Product update(Long id, Product product);

    boolean delete(long id);
}
