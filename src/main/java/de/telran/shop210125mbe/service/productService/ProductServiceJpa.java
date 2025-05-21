package de.telran.shop210125mbe.service.productService;

import de.telran.shop210125mbe.model.dto.ProductDto;
import de.telran.shop210125mbe.model.entity.CategoryEntity;
import de.telran.shop210125mbe.model.entity.ProductEntity;
import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.repository.CategoryRepository;
import de.telran.shop210125mbe.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @PostConstruct
    void init(){
        // создадим категории
        CategoryEntity category1 = CategoryEntity.builder()
                .name("Garden Tools")
                .build();

        CategoryEntity category2 = CategoryEntity.builder()
                .name("Outdoor Power Equipment")
                .build();

        CategoryEntity category3 = CategoryEntity.builder()
                .name("Watering Equipment")
                .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        // заполним таблицу Product тестовыми данными
        ProductEntity product1 = ProductEntity.builder()
                .name("Garden Trowel")
                .price(19.99)
                .discountPrice(15.99)
                .description("Sturdy garden trowel with wooden handle")
                .imageUrl("https://example.com/images/garden_trowel.jpg")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .category(category1)
                .build();
        // product1.setProductId(...); // это поле должно быть сгенерировано бд
        ProductEntity productNew1 = productRepository.save(product1); // сохраняем в базу данных
        System.out.println("productNew1 = " + productNew1);

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
                .category(category1)
                .build();
        productRepository.save(product2);

        ProductEntity product3 = ProductEntity.builder()
                .name("Gas Lawn Mower")
                .price(349.99)
                .discountPrice(299.99)
                .description("Gas-powered lawn mower with 21-inch cutting deck")
                .imageUrl("https://example.com/images/lawn_mower.jpg")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .category(category2)
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
                .category(category3)
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

    public ProductDto insertProduct(ProductDto newProductDto) {
        return null;
    }

    public ProductDto updateProduct(Long id, ProductDto updatedProductDto) {
        return null;
    }

    public ProductDto updatePartProduct(Long productId, ProductDto updatedProductDto) {
        return null;
    }

    public void deleteProductById(Long productId) {

    }
}
