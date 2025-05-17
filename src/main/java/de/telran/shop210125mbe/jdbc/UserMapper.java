package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Role;
import de.telran.shop210125mbe.pojo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long userId = rs.getLong("UserID");
        final String name = rs.getString("Name");
        final String email = rs.getString("Email");
        final String phoneNumber = rs.getString("PhoneNumber");
        final String passwordHash = rs.getString("PasswordHash");
        final Role role = Role.valueOf(rs.getString("Role"));
        return new User(userId, name, email, phoneNumber, passwordHash, role);
    }
}
