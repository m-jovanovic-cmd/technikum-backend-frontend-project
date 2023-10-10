package technikumbackendfrontendproject.Backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.model.DTO.CartDTO;
import technikumbackendfrontendproject.Backend.service.CartService;
import technikumbackendfrontendproject.Backend.service.EntityNotFoundException;

import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ResponseStatus(code = CREATED)
    @PostMapping
    public Cart create(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @GetMapping("/get{id}")
    public ResponseEntity<Cart> findCartByUserId(@PathVariable Long userID) {
        var cart = cartService.findByUserId(userID);
        if (cart.getPositions().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteCartById(@PathVariable Long id) {
        try {
            Cart cart = cartService.getCart(id);
            cartService.deleteCart(id);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update{id}")
    //find cart with userId, jeder user hat nur eine cart
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO updatedCartDto) {
        try {
            CartDTO updatedCart = cartService.updateCart(id, updatedCartDto);

            logger.info("Cart with id: " + id + " updated!");

            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
