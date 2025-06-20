package de.telran.shop210125mbe.service.categoryService;

import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.CategoryDto;
import de.telran.shop210125mbe.model.entity.CategoryEntity;
import de.telran.shop210125mbe.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;

@Service
@RequiredArgsConstructor
public class CategoryServiceJpa {

    private final CategoryRepository categoryRepository;

    private final Mappers mappers;

    @PostConstruct
        // @EventListener(ApplicationReadyEvent.class)
        // @Transactional
    void init() {
        System.out.println(YELLOW + "Category service JPA initialization" + RESET);
        // создадим категории
        CategoryEntity category1 = CategoryEntity.builder()
                .name("Garden Tools")
                .build();
        categoryRepository.save(category1);

        CategoryEntity category2 = CategoryEntity.builder()
                .name("Outdoor Power Equipment")
                .build();
        categoryRepository.save(category2);

        CategoryEntity category3 = CategoryEntity.builder()
                .name("Watering Equipment")
                .build();
        categoryRepository.save(category3);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(mappers::convertToCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        return categoryEntity != null ? mappers.convertToCategoryDto(categoryEntity) : null;
    }

    public CategoryDto createCategory(CategoryDto newCategoryDto) {
        if (newCategoryDto.getCategoryId() != null) {
            throw new IllegalArgumentException("CategoryID should not be defined.");
        }

//        CategoryEntity categoryEntity = CategoryEntity.builder()
//                .name(newCategoryDto.getName())
//                .build();
        CategoryEntity categoryEntity = categoryRepository.save(mappers.convertToCategoryEntity(newCategoryDto));
        return mappers.convertToCategoryDto(categoryEntity);
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
