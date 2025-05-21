package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.Favorite;
import de.telran.shop210125mbe.pojo.User;
import de.telran.shop210125mbe.service.favoriteService.FavoriteServiceInterface;
import de.telran.shop210125mbe.service.favoriteService.FavoriteServiceJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteServiceJpa favoriteServiceJpa;

    @GetMapping
    public List<Favorite> getAllFavorites(){
        System.out.println("Get all favorites");
        return favoriteServiceJpa.getAllFavorites();
    }

    @GetMapping("/{id}")
    public Favorite getFavorite(@PathVariable Long id) {
        System.out.println("Get " + id + " favorite");
        return favoriteServiceJpa.getFavoriteById(id);
    }

    @PostMapping
    public void insertFavorite(){
        System.out.println("Insert favorite");
    }

    @PutMapping
    public void updateFavorite(){
        System.out.println("Update favorite");
    }

    @DeleteMapping
    public void deleteFavorite(){
        System.out.println("Delete favorite");
    }
}
