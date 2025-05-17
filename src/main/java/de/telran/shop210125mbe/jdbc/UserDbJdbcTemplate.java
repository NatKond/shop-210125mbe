package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDbJdbcTemplate implements UserDbInterface {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        Map<String, Object> params = new HashMap<>(
                Map.of("ID", user.getUserId(),
                        "Name", user.getName(),
                        "Email", user.getEmail(),
                        "PhoneNumber", user.getPhoneNumber(),
                        "PasswordHash", user.getPasswordHash(),
                        "Role", user.getRole().name()
                ));
        String sql = "insert into Users (UserID, Name, Email, PhoneNumber, PasswordHash, Role)" +
                " values (:ID, :Name, :Email, :PhoneNumber, :PasswordHash, :Role)";
        jdbcTemplate.update(sql, params);
        return findById(user.getUserId());
    }

    @Override
    public User findById(Long id) {
        Map<String, Object> params = new HashMap<>(Map.of("ID", id));
        String sql = "select * from Users where UserID=:ID";
        return jdbcTemplate.queryForObject(sql, params, new UserMapper());
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from Users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User update(Long id, User user) {
        Map<String, Object> params = new HashMap<>(
                Map.of("ID", user.getUserId(),
                        "Name", user.getName(),
                        "Email", user.getEmail(),
                        "PhoneNumber", user.getPhoneNumber(),
                        "PasswordHash", user.getPasswordHash(),
                        "Role", user.getRole().name()
                ));
        String sql = "update Users set Name=:Name, Email=:Email, PhoneNumber=:PhoneNumber, PasswordHash=:PasswordHash, Role=:Role "+
                "where UserID=:ID";;
        return  jdbcTemplate.update(sql, params) > 0 ? findById(user.getUserId()) : save(user);
    }

    @Override
    public boolean delete(long id) {
        Map<String, Object> params = new HashMap<>(Map.of("ID", id));
        String sql = "delete from Users where UserID=:ID";
        int rowsAffected = jdbcTemplate.update(sql, params);
        return rowsAffected > 0;
    }
}
