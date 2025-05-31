package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.FavoriteDto;
import de.telran.shop210125mbe.pojo.Favorite;
import de.telran.shop210125mbe.pojo.User;
import de.telran.shop210125mbe.service.favoriteService.FavoriteServiceInterface;
import de.telran.shop210125mbe.service.favoriteService.FavoriteServiceJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;

@RestController
@RequestMapping(value = "/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteServiceJpa favoriteServiceJpa;

    @GetMapping
    public List<FavoriteDto> getAllFavorites() {
        System.out.println(YELLOW + "Get all favorites" + RESET);
        return favoriteServiceJpa.getAllFavorites();
    }

    @GetMapping("/{id}")
    public FavoriteDto getFavorite(@PathVariable Long id) {
        System.out.println(YELLOW + "Get " + id + " favorite" + RESET);
        return favoriteServiceJpa.getFavoriteById(id);
    }

    @PostMapping
    public void insertFavorite() {
        System.out.println(YELLOW + "Insert favorite" + RESET);
    }

    @PutMapping
    public void updateFavorite() {
        System.out.println(YELLOW + "Update favorite" + RESET);
    }

    @DeleteMapping
    public void deleteFavorite() {
        System.out.println(YELLOW + "Delete favorite" + RESET);
    }
}
