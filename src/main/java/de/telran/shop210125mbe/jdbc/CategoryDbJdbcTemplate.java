package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDbJdbcTemplate implements CategoryDbInterface {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Category save(Category category) {
        Map<String, Object> params = new HashMap<>(
                Map.of("ID", category.getCategoryId(),
                        "Name", category.getName()));
        String sql = "insert into Categories (CategoryID, Name) values (:ID,:Name)";
        jdbcTemplate.update(sql, params);
        return findById(category.getCategoryId());
    }

    @Transactional
    @Override
    public Category findById(Long id) {
        Map<String, Object> params = new HashMap<>(Map.of("ID", id));
        String sql = "select * from Categories where CategoryID=:ID";
        // return jdbcTemplate.queryForObject(sql, params, new CategoryMapper());
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Category.class)); //BeanPropertyRowMapper
    }

    @Transactional
    @Override
    public List<Category> findAll() {
        String sql = "select * from Categories";
       // return jdbcTemplate.query(sql, new CategoryMapper());
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    @Transactional
    @Override
    public Category update(Long id, Category category) {
        Map<String, Object> params = new HashMap<>(
                Map.of("ID", id,
                        "Name", category.getName()));
        String sql = "update Categories set Name=:Name where CategoryID=:ID";
        return jdbcTemplate.update(sql, params) > 0 ? findById(category.getCategoryId()) : save(category);
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        Map<String, Object> params = new HashMap<>(Map.of("ID", id));
        String sql = "delete from Categories where CategoryID=:ID";
        int rowsAffected = jdbcTemplate.update(sql, params);
        return rowsAffected > 0;
    }
}
