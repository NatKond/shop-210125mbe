package de.telran.shop210125mbe.controller;


import de.telran.shop210125mbe.model.dto.CategoryDto;
import de.telran.shop210125mbe.model.dto.ProductDto;
import de.telran.shop210125mbe.pojo.Category;
import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.service.productService.ProductServiceInterface;
import de.telran.shop210125mbe.service.productService.ProductServiceJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;

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
        System.out.println(YELLOW + "Get all products" + RESET);
        return productServiceJpa.getAllProducts();
    }

    @GetMapping("/{id}") // http://localhost:8080/product/find/1
    public ProductDto getProduct(@PathVariable(name = "id") Long productId) {
        System.out.println("Get " + productId + " product" + RESET);
        return productServiceJpa.getProductById(productId);
    }

    @GetMapping("/name/{name}") // http://localhost:8080/product/name/Pruning%20Shears
    public ProductDto getProductByName(@PathVariable String name) {
        System.out.println(YELLOW + "Get " + name + " product" + RESET);
        return productServiceJpa.getProductByName(name);
    }

    @GetMapping("/discount")
    public List<ProductDto> getProductsWithDiscount() {
        System.out.println(YELLOW + "Get all products with discount" + RESET);
        return productServiceJpa.getProductsWithDiscountPrice();
    }


    @GetMapping("/description") // http://localhost:8080/product/description?description=lawn%20mower&price=100
    public List<ProductDto> getProductsWithDescriptionContainingAndPriceGreaterThan(@RequestParam String description, @RequestParam Double price) {
        System.out.println(YELLOW + "Get all products with discount" + RESET);
        return productServiceJpa.getProductsWithDescriptionContainingAndPriceGreaterThan(description, price);
    }

    @GetMapping("/category")// http://localhost:8080/product/category?price=0
    public List<ProductDto> getProductsWithCategoryAndDiscountPriceGreaterThan(@RequestBody CategoryDto categoryDto, @RequestParam Double price) {
        System.out.println(YELLOW + "Get all products with discount" + RESET);
        return productServiceJpa.getProductsWithCategoryAndDiscountPriceGreaterThan(categoryDto, price);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductDto insertProduct(@RequestBody ProductDto newProductDto) {
        System.out.println(YELLOW + "Insert product" + RESET);
        return productServiceJpa.insertProduct(newProductDto);
    }

    // обновление всего объекта, если объекта не существует, мы должны создать новый
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable(name = "id") Long productId, @RequestBody ProductDto updatedProductDto) {
        System.out.println(YELLOW + "Update " + productId + " product" + RESET);
        return productServiceJpa.updateProduct(productId, updatedProductDto);
    }

    // обновление части информации, если объекта не существует, новый не создаем
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public ProductDto updatePartProduct(@PathVariable(name = "id") Long productId, @RequestBody ProductDto updatedProductDto) {
        System.out.println(YELLOW + "Update " + productId + " product partially" + RESET);
        return productServiceJpa.updatePartProduct(productId, updatedProductDto);
    }

    //удаление
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long productId) {
        System.out.println(YELLOW + "Delete " + productId + " product" + RESET);
        productServiceJpa.deleteProductById(productId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException exception) {
        return "Product controller: " + exception.getMessage();
    }
}
