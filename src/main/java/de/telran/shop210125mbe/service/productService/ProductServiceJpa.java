package de.telran.shop210125mbe.service.productService;

import de.telran.shop210125mbe.model.dto.ProductDto;
import de.telran.shop210125mbe.model.entity.ProductEntity;
import de.telran.shop210125mbe.repository.CategoryRepository;
import de.telran.shop210125mbe.repository.ProductRepository;
import de.telran.shop210125mbe.service.categoryService.CategoryServiceJpa;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductServiceJpa{

    // @Autowired
    private final ProductRepository productRepository;
    // @Autowired
    private final CategoryRepository categoryRepository;
    // private final CategoryServiceJpa categoryServiceJpa;

    // @PostConstruct
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    void init(){
        // заполним таблицу Product тестовыми данными
        ProductEntity product1 = ProductEntity.builder()
                .name("Garden Trowel")
                .price(19.99)
                .discountPrice(15.99)
                .description("Sturdy garden trowel with wooden handle")
                .imageUrl("https://example.com/images/garden_trowel.jpg")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .category(categoryRepository.findById(1L).orElse(null))
                .build();
        // product1.setProductId(...); // это поле должно быть сгенерировано бд
        //ProductEntity productNew1 =
                productRepository.save(product1); // сохраняем в базу данных
//        System.out.println("productNew1 = " + productNew1);

        product1.setPrice(29.99);
        product1.setDiscountPrice(19.99);
//        productNew1 = productRepository.save(product1);
//        System.out.println("productNew1 = " + productNew1);

        ProductEntity product2 = ProductEntity.builder()
                .name("Pruning Shears")
                .price(39.99)
                .discountPrice(35.99)
                .description("Heavy-duty pruning shears for trimming bushes and small branches")
                .imageUrl("https://example.com/images/pruning_shears.jpg")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .category(categoryRepository.findById(1L).orElse(null))
                .build();
        productRepository.save(product2);

        ProductEntity product3 = ProductEntity.builder()
                .name("Gas Lawn Mower")
                .price(349.99)
                .description("Gas-powered lawn mower with 21-inch cutting deck")
                .imageUrl("https://example.com/images/lawn_mower.jpg")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .category(categoryRepository.findById(2L).orElse(null))
                .build();
        productRepository.save(product3);

        ProductEntity product4 = ProductEntity.builder()
                .name("New Gas Lawn Mower")
                .price(849.99)
                .discountPrice(699.99)
                .description("Gas-powered lawn mower with 21-inch cutting deck")
                .imageUrl("https://example.com/images/lawn_mower.jpg")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .category(categoryRepository.findById(3L).orElse(null))
                .build();
        productRepository.save(product4);
    }

    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            ProductDto productDto = new ProductDto(productEntity.getProductId(),
                    productEntity.getName(),
                    productEntity.getDescription(),
                    productEntity.getPrice(),
                    productEntity.getImageUrl(),
                    productEntity.getDiscountPrice(),
                    productEntity.getCreatedAt(),
                    productEntity.getUpdatedAt(),
                    productEntity.getCategory().getCategoryId()
            );
            result.add(productDto);
        }
        return result ;
    }

    public ProductDto getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product with id = " + id + " is not found."));
        return ProductDto.builder()
                .productId(productEntity.getProductId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .imageUrl(productEntity.getImageUrl())
                .discountPrice(productEntity.getDiscountPrice())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .categoryId(productEntity.getCategory().getCategoryId())
                .build();
    }


    public ProductDto getProductByName(String name){
        ProductEntity productEntity = productRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Product with name = " + name + " is not found."));
        return ProductDto.builder()
                .productId(productEntity.getProductId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .imageUrl(productEntity.getImageUrl())
                .discountPrice(productEntity.getDiscountPrice())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .categoryId(productEntity.getCategory().getCategoryId())
                .build();
    }

    public List<ProductDto> getProductsWithDiscountPrice(){
        List<ProductEntity> productEntities = productRepository.findAllWithDiscountPrice();
        List<ProductDto> result = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            ProductDto productDto = new ProductDto(productEntity.getProductId(),
                    productEntity.getName(),
                    productEntity.getDescription(),
                    productEntity.getPrice(),
                    productEntity.getImageUrl(),
                    productEntity.getDiscountPrice(),
                    productEntity.getCreatedAt(),
                    productEntity.getUpdatedAt(),
                    productEntity.getCategory().getCategoryId()
            );
            result.add(productDto);
        }
        return result ;
    }

    public ProductDto insertProduct(ProductDto newProductDto) {
        if (newProductDto.getProductId() != null) {
            throw new IllegalArgumentException("ProductID should not be defined.");
        }
        ProductEntity productEntity = ProductEntity.builder()
                .name(newProductDto.getName())
                .price(newProductDto.getPrice())
                .discountPrice(newProductDto.getDiscountPrice())
                .description(newProductDto.getDescription())
                .imageUrl(newProductDto.getImageUrl())
                .createdAt(newProductDto.getCreatedAt())
                .updatedAt(newProductDto.getUpdatedAt())
                .category(categoryRepository.getReferenceById(newProductDto.getCategoryId()))
                .build();
        productRepository.save(productEntity);
        return getProductById(newProductDto.getProductId());
    }

    public ProductDto updateProduct(Long id, ProductDto updatedProductDto) {
        if (updatedProductDto.getProductId() != null) {
            throw new IllegalArgumentException("ProductID should not be defined.");
        }
        ProductEntity productEntity = productRepository.findById(id).orElse(new ProductEntity());
        productEntity.setName(updatedProductDto.getName());
        productEntity.setDescription(updatedProductDto.getDescription());
        productEntity.setPrice(updatedProductDto.getPrice());
        productEntity.setImageUrl(updatedProductDto.getImageUrl());
        productEntity.setDiscountPrice(updatedProductDto.getDiscountPrice());
        productEntity.setCreatedAt(updatedProductDto.getCreatedAt());
        productEntity.setUpdatedAt(updatedProductDto.getUpdatedAt());
        productEntity.setCategory(categoryRepository.getReferenceById(updatedProductDto.getCategoryId()));
        productRepository.save(productEntity);
        return getProductById(id);
    }

    public ProductDto updatePartProduct(Long productId, ProductDto updatedProductDto) {
        ProductEntity existingProductEntity = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product with id = " + productId + " is not found."));
        if (updatedProductDto.getName() != null &&
                !updatedProductDto.getName().equals(existingProductEntity.getName())) {
            existingProductEntity.setName(updatedProductDto.getName());
        }
        if (updatedProductDto.getDescription() != null &&
                !updatedProductDto.getDescription().equals(existingProductEntity.getDescription())) {
            existingProductEntity.setDescription(updatedProductDto.getDescription());
        }
        if (updatedProductDto.getPrice() != null &&
                !updatedProductDto.getPrice().equals(existingProductEntity.getPrice())) {
            existingProductEntity.setPrice(updatedProductDto.getPrice());
        }
        if (updatedProductDto.getDiscountPrice() != null &&
                !updatedProductDto.getDiscountPrice().equals(existingProductEntity.getDiscountPrice())) {
            existingProductEntity.setDiscountPrice(updatedProductDto.getDiscountPrice());
        }
        if (updatedProductDto.getCategoryId() != null &&
                !updatedProductDto.getCategoryId().equals(existingProductEntity.getCategory().getCategoryId())) {
            existingProductEntity.setCategory(categoryRepository.getReferenceById(updatedProductDto.getCategoryId()));
        }
        return null;
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
