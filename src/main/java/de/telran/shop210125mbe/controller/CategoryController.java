package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.Category;
import de.telran.shop210125mbe.service.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryServiceInterface categoryServiceInterface;

    @GetMapping
    public List<Category> getAllCategories(){
        System.out.println("Get all categories");
        return categoryServiceInterface.getAllCategories();
    }

    @PostMapping
    public void insertCategory(){
        System.out.println("Insert category");
    }

    @PutMapping
    public void updateCategory(){
        System.out.println("Update category");
    }

    @DeleteMapping
    public void deleteCategory(){
        System.out.println("Delete category");
    }
}
