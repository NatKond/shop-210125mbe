package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

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
        return localeStorage.stream().filter(product -> product.getProductId().equals(productId))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Product with id = " + productId + " is not found."));
    }

    @Override
    public Product insertProduct(Product newProduct) {
        if (newProduct.getProductId() != null &&
                localeStorage.stream()
                        .noneMatch(product -> product.getProductId().equals(newProduct.getProductId()))) {
            localeStorage.add(newProduct);
            return getProductById(newProduct.getProductId());
        }
        throw new RuntimeException("Product is not created. Either id = null or there is already a product with this id.");
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
                if (updatedProduct.getProductId() != null &&
                        !updatedProduct.getProductId().equals(product.getProductId())) {
                    product.setProductId(updatedProduct.getProductId());
                }
                if (updatedProduct.getName() != null &&
                        !updatedProduct.getName().equals(product.getName())) {
                    product.setName(updatedProduct.getName());
                }
                if (updatedProduct.getDescription() != null &&
                        !updatedProduct.getDescription().equals(product.getDescription())) {
                    product.setDescription(updatedProduct.getDescription());
                }
                if (updatedProduct.getPrice() != null &&
                        !updatedProduct.getPrice().equals(product.getPrice())) {
                    product.setPrice(updatedProduct.getPrice());
                }
                if (updatedProduct.getDiscountPrice() != null &&
                        !updatedProduct.getDiscountPrice().equals(product.getDiscountPrice())) {
                    product.setDiscountPrice(updatedProduct.getDiscountPrice());
                }
                if (updatedProduct.getCategoryId() != null &&
                        !updatedProduct.getCategoryId().equals(product.getCategoryId())) {
                    product.setCategoryId(updatedProduct.getCategoryId());
                }
                return product;
            }
        }
        throw new IllegalArgumentException("Product with id = " + productId + " is not found.");
    }

    @Override
    public void deleteProductById(Long productId) {
        if (localeStorage.removeIf(product -> product.getProductId().equals(productId))) return;
        throw new IllegalArgumentException("Product with id = " + productId + " is not found.");

//        Iterator<Product> iterator = localeStorage.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if (product.getProductId().equals(productId)) {
//                iterator.remove();
//            }
//        }
    }
}
