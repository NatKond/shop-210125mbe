package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDbJdbcTemplate implements ProductDbInterface {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Product save(Product product) {
        Map<String, Object> params = new HashMap<>(
                Map.of("ID", product.getProductId(),
                        "Name", product.getName(),
                        "Description", product.getDescription(),
                        "Price", product.getPrice(),
                        "ImageURL", product.getImageUrl(),
                        "DiscountPrice", product.getDiscountPrice(),
                        "CreatedAt", product.getCreatedAt(),
                        "UpdatedAt", product.getUpdatedAt(),
                        "CategoryID", product.getCategoryId()
                ));
        String sql = "insert into Products (ProductID, CategoryID, DiscountPrice, Price, CreatedAt, UpdatedAt, Description, ImageURL, Name)" +
                " values (:ID,:CategoryID, :DiscountPrice, :Price, :CreatedAt, :UpdatedAt, :Description, :ImageURL, :Name)";
        jdbcTemplate.update(sql, params);
        return findById(product.getProductId());
    }

    @Override
    public Product findById(Long id) {
        Map<String, Object> params = new HashMap<>(Map.of("ID", id));
        String sql = "select * from Products where ProductID=:ID";
        return jdbcTemplate.queryForObject(sql, params, new ProductMapper()); //BeanPropertyRowMapper
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from Products";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public Product update(Long id, Product product) {
        Map<String, Object> params = new HashMap<>(
                Map.of("ID", id,
                        "Name", product.getName(),
                        "Description", product.getDescription(),
                        "Price", product.getPrice(),
                        "ImageURL", product.getImageUrl(),
                        "DiscountPrice", product.getDiscountPrice(),
                        "CreatedAt", product.getCreatedAt(),
                        "UpdatedAt", product.getUpdatedAt(),
                        "CategoryID", product.getCategoryId()
                ));
        String sql = "update Products set Name=:Name, Description=:Description, Price=:Price, ImageURL=:ImageURL, DiscountPrice=:DiscountPrice," +
                "CreatedAt=:CreatedAt, UpdatedAt=:UpdatedAt, CategoryID=:CategoryID where ProductID=:ID";
        return jdbcTemplate.update(sql, params) > 0 ? findById(product.getProductId()) : save(product);
    }

    @Override
    public boolean delete(long id) {
        Map<String, Object> params = new HashMap<>(Map.of("ID", id));
        String sql = "delete from Products where ProductID=:ID";
        int rowsAffected = jdbcTemplate.update(sql, params);
        return rowsAffected > 0;
    }
}
