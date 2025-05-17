package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Category;

import java.util.List;

public interface CategoryDbInterface {

    Category save(Category category);

    Category findById(Long id);

    List<Category> findAll();

    Category update(Long id, Category category);

    boolean delete(long id);
}
