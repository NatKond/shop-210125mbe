package de.telran.shop210125mbe.model;

import java.util.Objects;

public class Favorite {

    private Long favoriteId;

    private Long userId;

    private Long productId;

    public Favorite(Long favoriteId, Long userId, Long productId) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.productId = productId;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(favoriteId, favorite.favoriteId) && Objects.equals(userId, favorite.userId) && Objects.equals(productId, favorite.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteId, userId, productId);
    }
}
