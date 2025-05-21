package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.CategoryDto;
import de.telran.shop210125mbe.pojo.Category;
import de.telran.shop210125mbe.service.categoryService.CategoryServiceInterface;
import de.telran.shop210125mbe.service.categoryService.CategoryServiceJpa;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/category") // localhost:8080/category
public class CategoryController {

//    @Autowired
//    @Qualifier("categoryJdbc")
//    private final CategoryServiceInterface categoryServiceInterface;

//    public CategoryController(@Qualifier("categoryJdbc")CategoryServiceInterface categoryServiceInterface) {
//        this.categoryServiceInterface = categoryServiceInterface;
//    }

    private final CategoryServiceJpa categoryServiceJpa;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        System.out.println("Get all categories");
        List<CategoryDto> categories = categoryServiceJpa.getAllCategories();
        return (categories != null) ? new ResponseEntity<>(categories, HttpStatusCode.valueOf(222)) :
                new ResponseEntity<>("Categories are not found.", HttpStatus.valueOf(404));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        System.out.println("Get " + id + " category");
        CategoryDto categoryDto = categoryServiceJpa.getCategoryById(id);
        return (categoryDto != null) ? ResponseEntity.ok(categoryDto) :
                new ResponseEntity<>("Category with id = " + id + " is not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto newCategoryDto) {
        System.out.println("Create category");
        CategoryDto categoryDto = categoryServiceJpa.createCategory(newCategoryDto);
        return (categoryDto != null) ? ResponseEntity.status(HttpStatus.CREATED).body(categoryDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category is not created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDto updatedCategoryDto) {
        System.out.println("Update " + id + " category");
        CategoryDto categoryDto = categoryServiceJpa.updateCategory(id, updatedCategoryDto);
        return (categoryDto != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category is not updated.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartCategory(@PathVariable Long id, @RequestBody CategoryDto updatedCategoryDto) {
        System.out.println("Update category " + id + " partially");
        CategoryDto categoryDto = categoryServiceJpa.updatePartCategory(id, updatedCategoryDto);
        return (categoryDto != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDto) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = " + id + " is not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        System.out.println("Delete " + id + " category");
        categoryServiceJpa.deleteCategoryById(id);
        return ResponseEntity.ok("Category with id = " + id + " is deleted.");
//        return (categoryServiceInterface.deleteCategoryById(id)) ? ResponseEntity.ok("Category with id = " + id + " is deleted.") :
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = " + id + " is not found.");
    }
}
