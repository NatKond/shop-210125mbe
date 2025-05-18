package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CartDbJdbcTemplate implements CartDbInterface {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Cart save(Cart cart) {
        Map<String, Object> params = new HashMap<>(
                Map.of("ID", cart.getCartId(),
                        "UserId", cart.getUserId())
        );
        String sql = "insert into Cart (CartID, UserID) values (:ID, :UserId)";
        jdbcTemplate.update(sql, params);
        return findById(cart.getCartId());
    }

    @Override
    public Cart findById(Long id) {
        Map<String, Object> params = new HashMap<>(Map.of("ID", id));
        String sql = "select * from Cart where CartID=:ID";
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Cart.class));
    }

    @Override
    public List<Cart> findAll() {
        String sql = "select * from Cart";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class));
    }

    @Override
    public Cart update(Long id, Cart cart) {
        Map<String, Object> params = new HashMap<>(
                Map.of("ID", id,
                        "UserID", cart.getUserId())
        );
        String sql = "update Cart set UserID=:UserId where CartID=:ID";
        return jdbcTemplate.update(sql, params) > 0 ? findById(id) : save(cart);
    }

    @Override
    public boolean delete(long id) {
        Map<String, Object> params = new HashMap<>(Map.of("ID", id));
        String sql = "delete from Cart where CartID=:ID";
        return jdbcTemplate.update(sql, params) > 0;
    }
}
