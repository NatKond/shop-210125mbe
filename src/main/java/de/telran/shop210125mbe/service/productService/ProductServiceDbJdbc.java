package de.telran.shop210125mbe.service.productService;

import de.telran.shop210125mbe.jdbc.ProductDbInterface;
import de.telran.shop210125mbe.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("productJdbc")
public class ProductServiceDbJdbc implements ProductServiceInterface {

    @Autowired
    ProductDbInterface productDbInterface;

    @Override
    public List<Product> getAllProducts() {
        try {
            return productDbInterface.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Products are not found.");
        }
    }

    @Override
    public Product getProductById(Long id) {
        try {
            return productDbInterface.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Product with id = " + id + " is not found.");
        }
    }

    @Override
    public Product insertProduct(Product newProduct) {
        try {
            return productDbInterface.save(newProduct);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Product is not created. Either id = null or there is already a user with this id.");
        }
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        try {
            return productDbInterface.update(id, updatedProduct);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Product is not created. Either id = null or there is already a user with this id.");
        }
    }

    @Override
    public Product updatePartProduct(Long productId, Product updatedProduct) {
        return null;
    }

    @Override
    public void deleteProductById(Long productId) {
        if (productDbInterface.delete(productId)) return;
        throw new NoSuchElementException("Product with id = " + productId + " is not found.");
    }
}
