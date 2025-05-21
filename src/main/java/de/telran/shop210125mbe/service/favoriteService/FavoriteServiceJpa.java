package de.telran.shop210125mbe.service.favoriteService;

import de.telran.shop210125mbe.pojo.Favorite;
import de.telran.shop210125mbe.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FavoriteServiceJpa {

    private final FavoriteRepository favoriteRepository;

    public List<Favorite> getAllFavorites() {
        return List.of();
    }

    public Favorite getFavoriteById(Long id) {
        return null;
    }

    public Favorite createFavorite(Favorite newFavorite) {
        return null;
    }

    public Favorite updateFavorite(Long id, Favorite updatedFavorite) {
        return null;
    }

    public Favorite updatePartFavorite(Long id, Favorite updatedFavorite) {
        return null;
    }

    public Boolean deleteFavoriteById(Long id) {
        return null;
    }
}
