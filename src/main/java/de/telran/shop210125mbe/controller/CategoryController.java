package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.Category;
import de.telran.shop210125mbe.service.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category") // localhost:8080/category
public class CategoryController {

    @Autowired
    @Qualifier("categoryJdbc")
    CategoryServiceInterface categoryServiceInterface;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        System.out.println("Get all categories");
        List<Category> categories = categoryServiceInterface.getAllCategories();
        return (categories != null) ? new ResponseEntity<>(categories, HttpStatusCode.valueOf(222)) :
                new ResponseEntity<>("Categories are not found.", HttpStatus.valueOf(404));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        System.out.println("Get " + id + " category");
        Category category = categoryServiceInterface.getCategoryById(id);
        return (category != null) ? ResponseEntity.ok(category) :
                new ResponseEntity<>("Category with id = " + id + " is not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category newCategory) {
        System.out.println("Create category");
        Category category = categoryServiceInterface.createCategory(newCategory);
        return (category != null) ? ResponseEntity.status(HttpStatus.CREATED).body(category) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category is not created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        System.out.println("Update " + id + " category");
        Category category = categoryServiceInterface.updateCategory(id, updatedCategory);
        return (category != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(category) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category is not updated.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        System.out.println("Update category " + id + " partially");
        Category category = categoryServiceInterface.updatePartCategory(id, updatedCategory);
        return (category != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(category) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = " + id + " is not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        System.out.println("Delete " + id + " category");
        return (categoryServiceInterface.deleteCategoryById(id)) ? ResponseEntity.ok("Category with id = " + id + " is deleted.") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = " + id + " is not found.");
    }
}
