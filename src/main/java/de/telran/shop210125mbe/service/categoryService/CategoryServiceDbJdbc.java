package de.telran.shop210125mbe.service.categoryService;

import de.telran.shop210125mbe.jdbc.CategoryDbInterface;
import de.telran.shop210125mbe.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryJdbc")
// имя компонента в контенере по умолчению будет categoryServiceDbJdbc
public class CategoryServiceDbJdbc implements CategoryServiceInterface {

    @Autowired
    @Qualifier("categoryDbJdbcTemplate")
    CategoryDbInterface categoryDbInterface;

    @Override
    public List<Category> getAllCategories() {
        try {
            return categoryDbInterface.findAll();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        try {
            return categoryDbInterface.findById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Category createCategory(Category newCategory) {
        try {
            return categoryDbInterface.save(newCategory);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        try {
            return categoryDbInterface.update(id, updatedCategory);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Category updatePartCategory(Long id, Category updatedCategory) {
        return null;
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        return categoryDbInterface.delete(id);
    }
}
