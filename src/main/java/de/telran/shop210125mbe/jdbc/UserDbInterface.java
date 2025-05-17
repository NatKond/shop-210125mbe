package de.telran.shop210125mbe.jdbc;
import de.telran.shop210125mbe.pojo.User;
import java.util.List;

public interface UserDbInterface {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    User update(Long id, User user);

    boolean delete(long id);
}
