package de.telran.shop210125mbe.controller;


import de.telran.shop210125mbe.model.Product;
import de.telran.shop210125mbe.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// HTTP: GET(получение), POST(передача), PUT(вставка), PATCH(редактирование), DELETE(удаление)
@RestController
@RequestMapping(value = "/product") // localhost:8080/product
public class ProductController {
    @Autowired
    ProductServiceInterface productServiceInterface; // = new ProductServiceList();

    @GetMapping
    public List<Product> getAllProducts(){
        System.out.println("Get all products");
        return productServiceInterface.getAllProducts();
    }

    @PostMapping
    public void insertProduct(){
        System.out.println("Insert product");
    }

    @PutMapping
    public void updateProduct(){
        System.out.println("Update product");
    }

    @DeleteMapping
    public void deleteProduct(){
        System.out.println("Delete product");
    }
}
