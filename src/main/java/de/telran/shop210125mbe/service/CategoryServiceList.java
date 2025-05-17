package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary // при конфликтных ситуациях, когда есть несколько компонентов, имплементирующих один интерфейс, выбирается этот
@Service
public class CategoryServiceList implements CategoryServiceInterface{

    List<Category> localeStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        Category category1 = new Category(1L, "Food");
        Category category2 = new Category(2L, "Clothes");
        Category category3 = new Category(3L, "Domestic chemicals");
        localeStorage.addAll(List.of(category1, category2, category3));
    }

    @Override
    public List<Category> getAllCategories() {
        return localeStorage;
    }

    @Override
    public Category getCategoryById(Long id) {
        return localeStorage.stream().filter(category -> category.getCategoryId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Category createCategory(Category newCategory) {
        if (newCategory.getCategoryId()!= null &&
                localeStorage.stream()
                        .noneMatch(category -> category.getCategoryId().equals(newCategory.getCategoryId()))) {
            localeStorage.add(newCategory);
            return getCategoryById(newCategory.getCategoryId());
        }

        return null;
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        if (updatedCategory.getCategoryId() == null) return null;
        for (int i = 0; i < localeStorage.size(); i++) {
            if (localeStorage.get(i).getCategoryId().equals(id)){
                localeStorage.set(i,updatedCategory);
                return localeStorage.get(i);
            }
        }
        return createCategory(updatedCategory);
    }

    @Override
    public Category updatePartCategory(Long id, Category updatedCategory) {
        for (Category category : localeStorage) {
            if (category.getCategoryId().equals(id)){
                if (updatedCategory.getCategoryId() != null &&
                !updatedCategory.getCategoryId().equals(category.getCategoryId())){
                    category.setCategoryId(updatedCategory.getCategoryId());
                }
                if (updatedCategory.getName() != null &&
                        !updatedCategory.getName().equals(category.getName())){
                    category.setName(updatedCategory.getName());
                }
                return category;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        return localeStorage.removeIf(category -> category.getCategoryId().equals(id));
        //throw new IllegalArgumentException("Invalid category index.");
    }
}
