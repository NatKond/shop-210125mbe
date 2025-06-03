package de.telran.shop210125mbe.mapper;

import de.telran.shop210125mbe.configure.MapperUtil;
import de.telran.shop210125mbe.model.dto.CartDto;
import de.telran.shop210125mbe.model.dto.FavoriteDto;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.entity.CartEntity;
import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import de.telran.shop210125mbe.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Mappers {
    private final ModelMapper modelMapper;

    public UserDto convertToUserDto(UserEntity usersEntity) {
        if (usersEntity == null) return new UserDto();
        modelMapper.typeMap(UserEntity.class, UserDto.class)
                .addMappings(mapper -> {
                            mapper.skip(UserDto::setPasswordHash);
                            //mapper.skip((userDto, userEntityNew) -> userDto.setPasswordHash(((UserEntity)userEntityNew).getPasswordHash()));
                            mapper.skip(UserDto::setPhoneNumber);
                            mapper.skip(UserDto::setEmail);
                            // mapper.skip(UserDto::setFavorites);
                        }
                        //.addMappings(mapper -> mapper.skip(UserDto::setFavorites)
                        //(userDto, userEntityNew) -> userDto.setPasswordHash(((UserEntity)userEntityNew).getPasswordHash()))
                );
        UserDto userDto = modelMapper.map(usersEntity, UserDto.class); //автомат
        userDto.setPasswordHash("*".repeat(10)); //изменить уже созданный объект

        // преобразовываем вручную
//        if (userEntity.getFavorites() != null && !userEntity.getFavorites().isEmpty()) {
//            Set<FavoriteDto> favoriteDtoSet = userEntity.getFavorites()
//                    .stream()
//                    .map(favoriteEntity -> modelMapper.map(favoriteEntity, FavoriteDto.class))
//                    .peek(System.out::println)
//                    .collect(Collectors.toSet());
//            userDto.setFavorites(favoriteDtoSet);
//        }


        // преобразовываем
//        if (usersEntity.getFavorites() != null) {
//            Set<FavoriteDto> favoritesDtoSet = MapperUtil.convertSet(usersEntity.getFavorites(), this::convertToFavoriteDto);
//            userDto.setFavorites(favoritesDtoSet);
//        }
//
//        CartDto cartDto = convertToCartDto(usersEntity.getCart()); // второй связанный объект
//        userDto.setCart(cartDto);
        return userDto;
    }

    public UserEntity convertToUserEntity(UserDto userDto){
        return modelMapper.map(userDto,UserEntity.class);
    }

    public FavoriteDto convertToFavoriteDto(FavoriteEntity favoriteEntity) {
        FavoriteDto favoriteDto = modelMapper.map(favoriteEntity, FavoriteDto.class); //автомат
        favoriteDto.setUserId(null);
        return favoriteDto;
    }

    public CartDto convertToCartDto(CartEntity cartEntity) {
        CartDto cartDto = null;
        if (cartEntity != null)
            cartDto = modelMapper.map(cartEntity, CartDto.class); //автомат
        return cartDto;
    }

}