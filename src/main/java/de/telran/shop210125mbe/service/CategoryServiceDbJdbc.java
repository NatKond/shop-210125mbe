package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.jdbc.CategoryDbInterface;
import de.telran.shop210125mbe.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("categoryJdbc")
// имя компонента в контенере по умолчению будет categoryServiceDbJdbc
public class CategoryServiceDbJdbc implements CategoryServiceInterface {

    @Autowired
    @Qualifier("categoryDbJdbcTemplate")
    CategoryDbInterface categoryDbInterface;

    @Override
    public List<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            categories.add(categoryDbInterface.findById(i));
        }
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDbInterface.findById(id);
    }

    @Override
    public Category createCategory(Category newCategory) {
        return null;
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        return null;
    }

    @Override
    public Category updatePartCategory(Long id, Category updatedCategory) {
        return null;
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        return null;
    }
}
