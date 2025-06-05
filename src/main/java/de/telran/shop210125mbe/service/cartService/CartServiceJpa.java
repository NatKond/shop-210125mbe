package de.telran.shop210125mbe.service.cartService;

import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.CartDto;
import de.telran.shop210125mbe.model.entity.CartEntity;
import de.telran.shop210125mbe.repository.CartRepository;
import de.telran.shop210125mbe.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;

@Service
@DependsOn("userServiceJpa")
@RequiredArgsConstructor
public class CartServiceJpa {

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    private final Mappers mappers;

    // @EventListener(ApplicationReadyEvent.class)
    @PostConstruct
    @Transactional
    void init() {
        // создадим категории
        System.out.println(YELLOW + "Cart service JPA initialization" + RESET);
        CartEntity cart1 = CartEntity.builder()
                .user(userRepository.findById(1L).orElse(null))
                .build();

        CartEntity cart2 = CartEntity.builder()
                .user(userRepository.findById(2L).orElse(null))
                .build();

        CartEntity cart3 = CartEntity.builder()
                .user(userRepository.findById(3L).orElse(null))
                .build();

        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);
    }

    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(mappers::convertToCartDto)
                .collect(Collectors.toList());
    }

    public CartDto getCartById(Long id) {
        CartEntity cartEntity = cartRepository.findById(id).orElse(null);
        return cartEntity != null ? mappers.convertToCartDto(cartEntity) : null;
    }

    public CartDto createCart(CartDto newCart) {
        if (newCart.getCartId() != null){
            throw new IllegalArgumentException("CartId should not be defined.");
        }

        CartEntity cartEntity = cartRepository.save(mappers.convertToCartEntity(newCart));
        return mappers.convertToCartDto(cartEntity);
    }

    public CartDto updateCart(Long id, CartDto updatedCart) {

        if (cartRepository.findById(updatedCart.getCartId()).isPresent()){
            CartEntity updatedCartEntity = mappers.convertToCartEntity(updatedCart);
            CartEntity returnedCartEntity = cartRepository.save(updatedCartEntity);
            return mappers.convertToCartDto(returnedCartEntity);
        }else {
            updatedCart.setCartId(null);
            return createCart(updatedCart);
        }
    }

    public CartDto updatePartCart(Long id, CartDto updatedCart) {
        return null;
    }

    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }
}
