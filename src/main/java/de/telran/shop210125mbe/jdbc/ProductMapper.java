package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long productId = rs.getLong("ProductID");
        final String name = rs.getString("Name");
        final String description = rs.getString("Description");
        final Double price = rs.getDouble("Price");
        final String imageUrl = rs.getString("ImageURL");
        final Double discountPrice = rs.getDouble("DiscountPrice");
        final Timestamp createdAt = rs.getTimestamp("CreatedAt");
        final Timestamp updatedAt = rs.getTimestamp("UpdatedAt");
        final Long categoryId = rs.getLong("CategoryID");
        return new Product(productId, name, description, price, imageUrl, discountPrice, createdAt, updatedAt, categoryId);
    }
}
