package de.telran.shop210125mbe.controller;


import de.telran.shop210125mbe.model.Product;
import de.telran.shop210125mbe.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// HTTP: GET(получение), POST(передача), PUT(вставка), PATCH(редактирование), DELETE(удаление)
@RestController
@RequestMapping(value = "/product") // localhost:8080/product
public class ProductController {
    @Autowired
    ProductServiceInterface productServiceInterface; // = new ProductServiceList();

    @GetMapping
    public List<Product> getAllProducts() {
        System.out.println("Get all products");
        return productServiceInterface.getAllProducts();
    }

    @GetMapping("/{id}") // http://localhost:8080/product/find/1
    public Product getProduct(@PathVariable(name = "id") Long productId) {
        System.out.println("Get " + productId + " product");
        return productServiceInterface.getProductById(productId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product insertProduct(@RequestBody Product newProduct) {
        System.out.println("Insert product");
        return productServiceInterface.insertProduct(newProduct);
    }

    // обновление всего объекта, если объекта не существует, мы должны создать новый
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(name = "id") Long productId, @RequestBody Product updatedProduct) {
        System.out.println("Update " + productId + " product");
        return productServiceInterface.updateProduct(productId, updatedProduct);
    }

    // обновление части информации, если объекта не существует, новый не создаем
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public Product updatePartProduct(@PathVariable(name = "id") Long productId, @RequestBody Product updatedProduct) {
        System.out.println("Update " + productId + " product partially");
        return productServiceInterface.updatePartProduct(productId, updatedProduct);
    }

    //удаление
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long productId) {
        System.out.println("Delete " + productId + " product");
        productServiceInterface.deleteProductById(productId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Product controller: " + exception.getMessage();
    }
}
