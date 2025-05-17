package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Product;

import java.util.List;

public interface ProductServiceInterface {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product insertProduct(Product newProduct);

    Product updateProduct(Long id, Product updatedProduct);

    Product updatePartProduct(Long productId, Product updatedProduct);

    void deleteProductById(Long productId);
}
