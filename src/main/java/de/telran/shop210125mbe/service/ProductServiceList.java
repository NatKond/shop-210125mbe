package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceList implements ProductServiceInterface{

    List<Product> localeStorage = new ArrayList<>();

    @PostConstruct
    void init(){
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

}
