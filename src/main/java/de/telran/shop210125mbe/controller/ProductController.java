package de.telran.shop210125mbe.controller;


import de.telran.shop210125mbe.model.dto.ProductDto;
import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.service.productService.ProductServiceInterface;
import de.telran.shop210125mbe.service.productService.ProductServiceJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// HTTP: GET(получение), POST(передача), PUT(вставка), PATCH(редактирование), DELETE(удаление)
@RestController
@RequestMapping(value = "/product") // localhost:8080/product
@RequiredArgsConstructor
public class ProductController {

//    @Autowired
//    @Qualifier("productJdbc")
//    ProductServiceInterface productServiceInterface; // = new ProductServiceList();

    private final ProductServiceJpa productServiceJpa;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        System.out.println("Get all products");
        return productServiceJpa.getAllProducts();
    }

    @GetMapping("/{id}") // http://localhost:8080/product/find/1
    public ProductDto getProduct(@PathVariable(name = "id") Long productId) {
        System.out.println("Get " + productId + " product");
        return productServiceJpa.getProductById(productId);
    }

    @GetMapping("/name/{name}") // http://localhost:8080/product/name/Pruning%20Shears
    public ProductDto getProductByName(@PathVariable String name) {
        System.out.println("Get " + name + " product");
        return productServiceJpa.getProductByName(name);
    }

    @GetMapping("/discount")
    public List<ProductDto>  getProductsWithDiscount() {
        System.out.println("Get all products with discount");
        return productServiceJpa.getProductsWithDiscountPrice();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductDto insertProduct(@RequestBody ProductDto newProductDto) {
        System.out.println("Insert product");
        return productServiceJpa.insertProduct(newProductDto);
    }

    // обновление всего объекта, если объекта не существует, мы должны создать новый
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable(name = "id") Long productId, @RequestBody ProductDto updatedProductDto) {
        System.out.println("Update " + productId + " product");
        return productServiceJpa.updateProduct(productId, updatedProductDto);
    }

    // обновление части информации, если объекта не существует, новый не создаем
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public ProductDto updatePartProduct(@PathVariable(name = "id") Long productId, @RequestBody ProductDto updatedProductDto) {
        System.out.println("Update " + productId + " product partially");
        return productServiceJpa.updatePartProduct(productId, updatedProductDto);
    }

    //удаление
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long productId) {
        System.out.println("Delete " + productId + " product");
        productServiceJpa.deleteProductById(productId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Product controller: " + exception.getMessage();
    }
}
