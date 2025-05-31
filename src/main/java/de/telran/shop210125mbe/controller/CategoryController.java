package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.CategoryDto;
import de.telran.shop210125mbe.service.categoryService.CategoryServiceJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static java.awt.Color.YELLOW;

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
        System.out.println(YELLOW + "Get all categories" + RESET);
        List<CategoryDto> categories = categoryServiceJpa.getAllCategories();
        System.out.println(categories);
        return (categories != null) ? new ResponseEntity<>(categories, HttpStatusCode.valueOf(222)) :
                new ResponseEntity<>("Categories are not found.", HttpStatus.valueOf(404));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        System.out.println(YELLOW + "Get " + id + " category" + RESET);
        CategoryDto categoryDto = categoryServiceJpa.getCategoryById(id);
        return (categoryDto != null) ? ResponseEntity.ok(categoryDto) :
                new ResponseEntity<>("Category with id = " + id + " is not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto newCategoryDto) {
        System.out.println(YELLOW + "Create category" + RESET);
        CategoryDto categoryDto = categoryServiceJpa.createCategory(newCategoryDto);
        return (categoryDto != null) ? ResponseEntity.status(HttpStatus.CREATED).body(categoryDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category is not created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDto updatedCategoryDto) {
        System.out.println(YELLOW + "Update " + id + " category" + RESET);
        CategoryDto categoryDto = categoryServiceJpa.updateCategory(id, updatedCategoryDto);
        return (categoryDto != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category is not updated.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartCategory(@PathVariable Long id, @RequestBody CategoryDto updatedCategoryDto) {
        System.out.println(YELLOW + "Update category " + id + " partially" + RESET);
        CategoryDto categoryDto = categoryServiceJpa.updatePartCategory(id, updatedCategoryDto);
        return (categoryDto != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDto) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = " + id + " is not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        System.out.println(YELLOW + "Delete " + id + " category" + RESET);
        categoryServiceJpa.deleteCategoryById(id);
        return ResponseEntity.ok("Category with id = " + id + " is deleted.");
//        return (categoryServiceInterface.deleteCategoryById(id)) ? ResponseEntity.ok("Category with id = " + id + " is deleted.") :
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id = " + id + " is not found.");
    }
}
