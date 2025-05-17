package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CategoryDbJdbc implements CategoryDbInterface {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String userDb;

    @Value("${spring.datasource.password}")
    private String passwordDb;

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category findById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    jdbcUrl, userDb, passwordDb);
            statement = connection.prepareStatement(
                    "select CategoryID, Name from Categories where CategoryID=?");
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Category category = null;
            if (resultSet.next()) {
                category = new Category(
                        (long) resultSet.getInt("CategoryID"),
                        resultSet.getString("Name"));
            }
            return category;
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.getStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.getStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.getStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category update(Long id, Category category) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
