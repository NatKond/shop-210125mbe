package de.telran.shop210125mbe.service.cartService;

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
public class CartServiceJpa{

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    // @EventListener(ApplicationReadyEvent.class)
    @PostConstruct
    @Transactional
    void init(){
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

    public List<CartDto> getAllCarts(){
        return cartRepository.findAll().stream()
                .map(cartEntity -> CartDto.builder()
                        .cartId(cartEntity.getCartId())
                        .userId(cartEntity.getUser().getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    public CartDto getCartById(Long id){
        CartEntity cartEntity = cartRepository.findById(id).orElse(null);
        return cartEntity != null ? CartDto.builder()
                .cartId(cartEntity.getCartId())
                .userId(cartEntity.getUser().getUserId())
                .build() : null;
    }

    public CartDto createCart(CartDto newCart) {
        return null;
    }

    public CartDto updateCart(Long id, CartDto updatedCart) {
        return null;
    }

    public CartDto updatePartCart(Long id, CartDto updatedCart) {
        return null;
    }

    public Boolean deleteCartById(Long id) {
        return null;
    }
}
