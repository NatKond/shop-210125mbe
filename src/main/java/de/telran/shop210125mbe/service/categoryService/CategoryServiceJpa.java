package de.telran.shop210125mbe.service.categoryService;

import de.telran.shop210125mbe.model.dto.CategoryDto;
import de.telran.shop210125mbe.model.entity.CategoryEntity;
import de.telran.shop210125mbe.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceJpa {

    private final CategoryRepository categoryRepository;

    @PostConstruct
    void init() {
        // создадим категории
//        CategoryEntity category1 = CategoryEntity.builder()
//                .name("Garden Tools")
//                .build();
//
//        CategoryEntity category2 = CategoryEntity.builder()
//                .name("Outdoor Power Equipment")
//                .build();
//
//        CategoryEntity category3 = CategoryEntity.builder()
//                .name("Watering Equipment")
//                .build();
//
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
    }


    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map((categoryEntity) -> CategoryDto.builder()
                        .categoryId(categoryEntity.getCategoryId())
                        .name(categoryEntity.getName())
                        .build())
                .toList();
    }

    public CategoryDto getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if (categoryEntity == null) {
            return null;
        }
        return CategoryDto.builder()
                .categoryId(categoryEntity.getCategoryId())
                .name(categoryEntity.getName())
                .build();
    }

    public CategoryDto createCategory(CategoryDto newCategoryDto) {
        if (newCategoryDto.getCategoryId() != null) {
            throw new IllegalArgumentException("CategoryID should not be defined.");
        }

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(newCategoryDto.getName())
                .build();
        categoryRepository.save(categoryEntity);
        return getCategoryById(categoryEntity.getCategoryId());
    }

    public CategoryDto updateCategory(Long id, CategoryDto updatedCategoryDto) {
        if (updatedCategoryDto.getCategoryId() != null) {
            throw new IllegalArgumentException("CategoryID should not be defined.");
        }
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(new CategoryEntity());
        categoryEntity.setName(updatedCategoryDto.getName());
        categoryRepository.save(categoryEntity);
        return getCategoryById(id);
    }

    public CategoryDto updatePartCategory(Long id, CategoryDto updatedCategoryDto) {
        CategoryEntity existingCategoryEntity = categoryRepository.findById(id).orElse(null);
        if (existingCategoryEntity == null) {
            return null;
        }
        if (updatedCategoryDto.getName() != null &&
                !updatedCategoryDto.getName().equals(existingCategoryEntity.getName())) {
            existingCategoryEntity.setName(updatedCategoryDto.getName());
        }
        categoryRepository.save(existingCategoryEntity);
        return getCategoryById(id);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
