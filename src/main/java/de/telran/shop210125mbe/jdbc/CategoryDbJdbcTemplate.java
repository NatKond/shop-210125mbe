package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CategoryDbJdbcTemplate implements CategoryDbInterface{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Category findById(long id) {
        System.out.println("CategoryDbJdbcTemplate is working");
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);
        String sql = "select CategoryID, Name from Categories where CategoryID=:ID";
        return jdbcTemplate.queryForObject(sql, params, new CategoryMapper());
    }
}
