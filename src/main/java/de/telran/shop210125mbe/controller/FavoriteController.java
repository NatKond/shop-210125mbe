package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.Favorite;
import de.telran.shop210125mbe.service.FavoriteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/favorite")
public class FavoriteController {
    @Autowired
    FavoriteServiceInterface favoriteServiceInterface;

    @GetMapping
    public List<Favorite> getAllFavorites(){
        System.out.println("Get all favorites");
        return favoriteServiceInterface.getAllFavorites();
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
