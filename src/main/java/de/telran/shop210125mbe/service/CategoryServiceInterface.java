package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Category;

import java.util.List;

public interface CategoryServiceInterface {

    List<Category> getAllCategories();

    Category getCategoryById(Long id) ;

    Category createCategory(Category newCategory);

    Category updateCategory(Long id, Category updatedCategory);

    Category updatePartCategory(Long id, Category updatedCategory);

    Boolean deleteCategoryById(Long id);
}
