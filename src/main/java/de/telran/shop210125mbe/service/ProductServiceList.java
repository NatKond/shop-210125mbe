package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Component
public class ProductServiceList implements ProductServiceInterface {

    List<Product> localeStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Carrot");

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Broccoli");
        localeStorage.addAll(List.of(product1, product2));
    }

    @Override
    public List<Product> getAllProducts() {
        return localeStorage;
    }

    @Override
    public Product getProductById(Long productId) {
        return localeStorage.stream().filter(product -> product.getProductId().equals(productId)).findAny().orElse(null);
    }

    @Override
    public Product insertProduct(Product newProduct) {
        localeStorage.add(newProduct);
        return getProductById(newProduct.getProductId());
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        for (int i = 0; i < localeStorage.size(); i++) {
            if (localeStorage.get(i).getProductId().equals(productId)) {
                localeStorage.set(i, updatedProduct);
                return localeStorage.get(i);
            }
        }
        return insertProduct(updatedProduct);
    }

    @Override
    public Product updatePartProduct(Long productId, Product updatedProduct) {
        for (Product product : localeStorage) {
            if (product.getProductId().equals(productId)) {
                if (!Objects.equals(product.getProductId(), updatedProduct.getProductId())) {
                    product.setProductId(updatedProduct.getProductId());
                }
                if (!Objects.equals(product.getName(), updatedProduct.getName())) {
                    product.setName(updatedProduct.getName());
                }
                if (!Objects.equals(product.getDescription(), updatedProduct.getDescription())) {
                    product.setDescription(updatedProduct.getDescription());
                }
                if (!Objects.equals(product.getPrice(), updatedProduct.getPrice())) {
                    product.setPrice(updatedProduct.getPrice());
                }
                if (!Objects.equals(product.getDiscountPrice(), updatedProduct.getDiscountPrice())) {
                    product.setDiscountPrice(updatedProduct.getDiscountPrice());
                }
                if (!Objects.equals(product.getCategoryId(), updatedProduct.getCategoryId())) {
                    product.setCategoryId(updatedProduct.getCategoryId());
                }
                return product;
            }
        }
        return null;
    }

    @Override
    public void deleteProductById(Long productId) {
        if (localeStorage.removeIf(product -> product.getProductId().equals(productId))) return;
        throw new IllegalArgumentException("Invalid product index.");

//        Iterator<Product> iterator = localeStorage.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if (product.getProductId().equals(productId)) {
//                iterator.remove();
//            }
//        }
    }
}
