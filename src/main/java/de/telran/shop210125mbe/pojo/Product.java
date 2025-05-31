package de.telran.shop210125mbe.pojo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

// @Component("carrot") // явно задаем имя переменной в контейнере
@Component // по умолчанию имя переменной будет равно имени класса с маленькой буквы
public class Product {

    private Long productId;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Double discountPrice;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Long categoryId;

    @PostConstruct
    void init(){
        System.out.println("+ Выполняем какие-то действия после создания объекта, перед его использованием " + this);
    }

    @PreDestroy
    void destroy(){
        System.out.println("- Выполняем какие-то действия перед уничтожением объекта " + this);
    }

    public Product() {
    }

    public Product(Long productId, String name, String description, Double price, String imageUrl, Double discountPrice, Timestamp createdAt, Timestamp updatedAt, Long categoryId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.discountPrice = discountPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoryId = categoryId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", discountPrice=" + discountPrice +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", categoryId=" + categoryId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(name, product.name)
                && Objects.equals(description, product.description) && Objects.equals(price, product.price)
                && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(discountPrice, product.discountPrice)
                && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt)
                && Objects.equals(categoryId, product.categoryId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(productId);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Objects.hashCode(imageUrl);
        result = 31 * result + Objects.hashCode(discountPrice);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        result = 31 * result + Objects.hashCode(categoryId);
        return result;
    }
}