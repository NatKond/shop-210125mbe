package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.CartDto;

import de.telran.shop210125mbe.service.cartService.CartServiceJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.telran.shop210125mbe.textFormatting.*;

@RestController
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class CartController {

//    @Autowired
//    @Qualifier("cartJdbc")
//    private final CartServiceInterface cartServiceInterface;

    private final CartServiceJpa cartServiceJpa;

    @GetMapping
    public ResponseEntity<?> getAllCarts() {
        System.out.println(YELLOW + "Get all carts" + RESET);
        List<CartDto> carts = cartServiceJpa.getAllCarts();
        return (carts != null) ? new ResponseEntity<>(carts, HttpStatus.OK) :
                new ResponseEntity<>("Carts are not found.", HttpStatus.valueOf(404));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        System.out.println(YELLOW + "Get all " + id + " cart" + RESET);
        CartDto cart = cartServiceJpa.getCartById(id);
        return (cart != null) ? new ResponseEntity<>(cart, HttpStatus.OK) :
                new ResponseEntity<>("Cart with id = " + id + " is not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody CartDto newCart) {
        System.out.println(YELLOW + "Create Cart" + RESET);
        CartDto cart = cartServiceJpa.createCart(newCart);
        return (cart != null) ? ResponseEntity.status(HttpStatus.CREATED).body(cart) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is not created.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable Long id, @RequestBody CartDto updatedCart) {
        System.out.println(YELLOW + "Update " + id + " cart" + RESET);
        CartDto cart = cartServiceJpa.updateCart(id, updatedCart);
        return (cart != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(cart) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is not updated.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartCart(@PathVariable Long id, @RequestBody CartDto updatedCart) {
        System.out.println(YELLOW + "Update cart" + id + "partially" + RESET);
        CartDto cart = cartServiceJpa.updatePartCart(id, updatedCart);
        return (cart != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(cart) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with id = " + id + " is not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        System.out.println(YELLOW + "Delete cart" + id + RESET);
//        return (cartServiceJpa.deleteCartById(id)) ? ResponseEntity.ok().body("Cart with id = " + id + " is deleted.") :
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with id = " + id + " is not found.");
        return ResponseEntity.ok().body("Cart with id = " + id + " is deleted.");
    }
}
