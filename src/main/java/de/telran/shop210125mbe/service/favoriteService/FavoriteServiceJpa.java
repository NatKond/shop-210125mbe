package de.telran.shop210125mbe.service.favoriteService;

import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.FavoriteDto;
import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import de.telran.shop210125mbe.pojo.Favorite;
import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.repository.FavoriteRepository;
import de.telran.shop210125mbe.repository.ProductRepository;
import de.telran.shop210125mbe.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;

@Service
@DependsOn({"userServiceJpa", "productServiceJpa"})
@RequiredArgsConstructor
public class FavoriteServiceJpa {

    private final FavoriteRepository favoriteRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final Mappers mappers;

    @PostConstruct
    void init() {
        System.out.println(YELLOW + "Favorite service JPA initialization" + RESET);
        FavoriteEntity favorite1 = FavoriteEntity.builder()
                .user(userRepository.findById(1L).orElse(null))
                .product(productRepository.findById(2L).orElse(null))
                .build();
        favoriteRepository.save(favorite1);

        FavoriteEntity favorite2 = FavoriteEntity.builder()
                .user(userRepository.findById(1L).orElse(null))
                .product(productRepository.findById(1L).orElse(null))
                .build();
        favoriteRepository.save(favorite2);

        FavoriteEntity favorite3 = FavoriteEntity.builder()
                .user(userRepository.findById(1L).orElse(null))
                .product(productRepository.findById(3L).orElse(null))
                .build();
        favoriteRepository.save(favorite3);

        FavoriteEntity favorite4 = FavoriteEntity.builder()
                .user(userRepository.findById(2L).orElse(null))
                .product(productRepository.findById(4L).orElse(null))
                .build();
        favoriteRepository.save(favorite4);

        FavoriteEntity favorite5 = FavoriteEntity.builder()
                .user(userRepository.findById(3L).orElse(null))
                .product(productRepository.findById(1L).orElse(null))
                .build();
        favoriteRepository.save(favorite5);

        FavoriteEntity favorite6 = FavoriteEntity.builder()
                .user(userRepository.findById(3L).orElse(null))
                .product(productRepository.findById(2L).orElse(null))
                .build();
        favoriteRepository.save(favorite6);
    }

    public List<FavoriteDto> getAllFavorites() {
        return favoriteRepository.findAll().stream()
                .map(mappers::convertToFavoriteDto)
                .collect(Collectors.toList());
    }

    public FavoriteDto getFavoriteById(Long id) {
        return null;
    }

    public FavoriteDto createFavorite(FavoriteDto newFavorite) {
        return null;
    }

    public FavoriteDto updateFavorite(Long id, FavoriteDto updatedFavorite) {
        return null;
    }

    public FavoriteDto updatePartFavorite(Long id, FavoriteDto updatedFavorite) {
        return null;
    }

    public Boolean deleteFavoriteById(Long id) {
        return null;
    }


}
