package de.telran.shop210125mbe.service.favoriteService;

import de.telran.shop210125mbe.pojo.Favorite;

import java.util.List;

public interface FavoriteServiceInterface {

    List<Favorite> getAllFavorites();

    Favorite getFavoriteById(Long id);

    Favorite createFavorite(Favorite newFavorite);

    Favorite updateFavorite(Long id, Favorite updatedFavorite);

    Favorite updatePartFavorite(Long id, Favorite updatedFavorite);

    Boolean deleteFavoriteById(Long id);
}
