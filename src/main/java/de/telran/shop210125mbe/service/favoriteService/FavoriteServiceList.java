package de.telran.shop210125mbe.service.favoriteService;

import de.telran.shop210125mbe.pojo.Favorite;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavoriteServiceList implements FavoriteServiceInterface{

    List<Favorite> localeStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        Favorite favorite1 = new Favorite(1L,1L,1L);
        Favorite favorite2 = new Favorite(2L,2L,2L);
        localeStorage.addAll(List.of(favorite1,favorite2));
    }


    @Override
    public List<Favorite> getAllFavorites() {
        return localeStorage;
    }

    @Override
    public Favorite getFavoriteById(Long id) {
        return null;
    }

    @Override
    public Favorite createFavorite(Favorite newFavorite) {
        return null;
    }

    @Override
    public Favorite updateFavorite(Long id, Favorite updatedFavorite) {
        return null;
    }

    @Override
    public Favorite updatePartFavorite(Long id, Favorite updatedFavorite) {
        return null;
    }

    @Override
    public Boolean deleteFavoriteById(Long id) {
        return null;
    }
}
