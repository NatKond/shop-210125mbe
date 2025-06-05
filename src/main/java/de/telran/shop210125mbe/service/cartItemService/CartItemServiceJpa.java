package de.telran.shop210125mbe.service.cartItemService;

import de.telran.shop210125mbe.configure.MapperUtil;
import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.CartItemDto;
import de.telran.shop210125mbe.model.entity.CartItemEntity;
import de.telran.shop210125mbe.pojo.CartItem;
import de.telran.shop210125mbe.repository.CartItemRepository;
import de.telran.shop210125mbe.repository.CartRepository;
import de.telran.shop210125mbe.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static de.telran.shop210125mbe.textFormatting.RESET;
import static de.telran.shop210125mbe.textFormatting.YELLOW;

@Service
@RequiredArgsConstructor
@DependsOn({"cartServiceJpa", "productServiceJpa"})
public class CartItemServiceJpa {

    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final Mappers mappers;

    @PostConstruct
    void init() {
        System.out.println(YELLOW + "Cart item service JPA initialization" + RESET);
        CartItemEntity cartItemEntity1 = CartItemEntity.builder()
                .cart(cartRepository.getById(1L))
                .product(productRepository.getById(4L))
                .quantity(2)
                .build();
        cartItemRepository.save(cartItemEntity1);

        CartItemEntity cartItemEntity2 = CartItemEntity.builder()
                .cart(cartRepository.getById(1L))
                .product(productRepository.getById(2L))
                .quantity(1)
                .build();
        cartItemRepository.save(cartItemEntity2);

        CartItemEntity cartItemEntity3 = CartItemEntity.builder()
                .cart(cartRepository.getById(2L))
                .product(productRepository.getById(1L))
                .quantity(3)
                .build();
        cartItemRepository.save(cartItemEntity3);

        CartItemEntity cartItemEntity4 = CartItemEntity.builder()
                .cart(cartRepository.getById(3L))
                .product(productRepository.getById(3L))
                .quantity(1)
                .build();
        cartItemRepository.save(cartItemEntity4);
    }

    public List<CartItemDto> getAllCartItems() {
        return MapperUtil.convertList(cartItemRepository.findAll(), mappers::convertToCartItemDto);
    }

    public CartItemDto getCartItemById(Long id) {
        CartItemEntity cartItemEntity = cartItemRepository.findById(id).orElseThrow(()->new NoSuchElementException("Cart Item with id = " + id + " is not found."));
        return mappers.convertToCartItemDto(cartItemEntity);
    }

    public CartItemDto createCartItem(CartItemDto newCartItemDto) {
        if (newCartItemDto.getCartItemId() != null) {
            throw new IllegalArgumentException("CartItemID should not be defined.");
        }
        CartItemEntity cartItemEntity = mappers.convertToCartItemEntity(newCartItemDto);
        cartItemRepository.save(cartItemEntity);
        return getCartItemById(cartItemEntity.getCartItemId());
    }

    public CartItemDto updateCartItem(Long id, CartItemDto updatedCartItem) {

        CartItemEntity cartItemEntity = mappers.convertToCartItemEntity(updatedCartItem);

        if(cartItemRepository.findById(updatedCartItem.getCartItemId()).isPresent()){
            CartItemEntity returnedCartItemEntity = cartItemRepository.save(cartItemEntity);
            return mappers.convertToCartItemDto(returnedCartItemEntity);
        }else {
            cartItemEntity.setCartItemId(null);
            return createCartItem(updatedCartItem);
        }
    }

    public void deleteCartItemById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
