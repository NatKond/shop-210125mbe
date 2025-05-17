package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("CategoryID");
        final String name = rs.getString("Name");
        return new Category(id, name);
    }
}
