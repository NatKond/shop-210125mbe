package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceList implements CategoryServiceInterface{

    List<Category> localeStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        Category category1 = new Category(1L, "Food");
        Category category2 = new Category(2L, "Сlothes");
        localeStorage.addAll(List.of(category1, category2));
    }


    @Override
    public List<Category> getAllCategories() {
        return localeStorage;
    }
}
