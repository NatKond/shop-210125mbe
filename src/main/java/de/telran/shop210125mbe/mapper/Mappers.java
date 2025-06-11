package de.telran.shop210125mbe.mapper;

import de.telran.shop210125mbe.configure.MapperUtil;
import de.telran.shop210125mbe.model.dto.*;
import de.telran.shop210125mbe.model.entity.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Mappers {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init(){
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setAmbiguityIgnored(true);
    }

    public UserDto convertToUserDto(UserEntity userEntity) {
        if (userEntity == null) return new UserDto();

        modelMapper.typeMap(UserEntity.class, UserDto.class)
                .addMappings(mapper -> {
                            mapper.skip(UserDto::setPasswordHash);
                            //mapper.skip((userDto, userEntityNew) -> userDto.setPasswordHash(((UserEntity)userEntityNew).getPasswordHash()));
                            mapper.skip(UserDto::setPhoneNumber);
                            mapper.skip(UserDto::setEmail);
                            mapper.skip(UserDto::setFavorites);
                            // mapper.map(user->user.getCart().getCartId(), UserDto::setCartId);
                        }
                );

        UserDto userDto = modelMapper.map(userEntity, UserDto.class); //автомат
        userDto.setPasswordHash("*".repeat(10)); //изменить уже созданный объект

        // преобразовываем при помощи modelMapper
//        if (userEntity.getFavorites() != null && !userEntity.getFavorites().isEmpty()) {
//            Set<FavoriteDto> favoriteDtoSet = userEntity.getFavorites()
//                    .stream()
//                    .map(favoriteEntity -> modelMapper.map(favoriteEntity, FavoriteDto.class))
//                    .peek(System.out::println)
//                    .collect(Collectors.toSet());
//            userDto.setFavorites(favoriteDtoSet);
//        }


        // преобразовываем Set<FavoriteDto> при помощи методов этого класса
        if (userEntity.getFavorites() != null) {
            Set<FavoriteDto> favoritesDtoSet = MapperUtil.convertSet(userEntity.getFavorites(), this::convertToFavoriteDto);
            userDto.setFavorites(favoritesDtoSet);
        }
        CartDto cartDto = convertToCartDto(userEntity.getCart()); // второй связанный объект
        userDto.setCart(cartDto);

        return userDto;
    }

    public UserLimitedDto convertToUserLimitedDto(UserEntity userEntity) {
        if (userEntity == null) return new UserLimitedDto();

        modelMapper.typeMap(UserEntity.class, UserLimitedDto.class)
                .addMappings(mapper -> {
                            mapper.skip(UserLimitedDto::setPhoneNumber);
                            mapper.skip(UserLimitedDto::setEmail);
                        }
                );

        return modelMapper.map(userEntity, UserLimitedDto.class);
    }

    public UserEntity convertToUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }


    public FavoriteDto convertToFavoriteDto(FavoriteEntity favoriteEntity) {
        FavoriteDto favoriteDto = null;
        if (favoriteEntity != null) {
//            modelMapper.typeMap(FavoriteEntity.class, FavoriteDto.class).addMappings(
//                    (mapper) -> mapper.skip(FavoriteDto::setProduct));
            favoriteDto = modelMapper.map(favoriteEntity, FavoriteDto.class); //автомат
            favoriteDto.setUserId(null);
        }
        return favoriteDto;
    }

    public FavoriteEntity convertToFavoriteEntity(FavoriteDto favoriteDto) {
        return modelMapper.map(favoriteDto, FavoriteEntity.class);
    }

    public CartDto convertToCartDto(CartEntity cartEntity) {
        CartDto cartDto = null;
        if (cartEntity != null) {
            modelMapper.typeMap(CartEntity.class, CartDto.class)
                    .addMappings(mapper -> {
                                mapper.skip(CartDto::setCartItems);
                                mapper.skip(CartDto::setUserId);
                            }
                    );

            cartDto = modelMapper.map(cartEntity, CartDto.class);
            cartDto.setCartItems(MapperUtil.convertSet(cartEntity.getCartItems(), this::convertToCartItemDto));
        }
        return cartDto;
    }

    public CartEntity convertToCartEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, CartEntity.class);
    }

    public CategoryDto convertToCategoryDto(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return new CategoryDto();
        }
        return modelMapper.map(categoryEntity, CategoryDto.class);
    }

    public CategoryEntity convertToCategoryEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, CategoryEntity.class);
    }

    public ProductDto convertToProductDto(ProductEntity productEntity) {
        ProductDto productDto = null;
        if (productEntity != null) {
            productDto = modelMapper.map(productEntity, ProductDto.class);
        }
        return productDto;
    }

    public ProductLimitedDto convertToProductLimitedDto(ProductEntity productEntity) {
        ProductLimitedDto productLimitedDto = null;
        if (productEntity != null) {
            productLimitedDto = modelMapper.map(productEntity, ProductLimitedDto.class);
        }
        return productLimitedDto;
    }

    public ProductEntity convertToProductEntity(ProductDto productDto) {
        return modelMapper.map(productDto, ProductEntity.class);
    }

    public CartItemDto convertToCartItemDto(CartItemEntity cartItemEntity) {
        if (cartItemEntity == null) {
            return new CartItemDto();
        }
        modelMapper.typeMap(CartItemEntity.class, CartItemDto.class)
                .addMappings(mapper ->{
                    mapper.map(cartItem -> cartItem.getCart().getCartId(), CartItemDto::setCartId);
                });
        CartItemDto cartItemDto = modelMapper.map(cartItemEntity, CartItemDto.class);
        cartItemDto.setCartId(cartItemEntity.getCart().getCartId());

        return modelMapper.map(cartItemEntity, CartItemDto.class);
    }

    public CartItemEntity convertToCartItemEntity(CartItemDto cartItemDto) {
        return modelMapper.map(cartItemDto, CartItemEntity.class);
    }
}