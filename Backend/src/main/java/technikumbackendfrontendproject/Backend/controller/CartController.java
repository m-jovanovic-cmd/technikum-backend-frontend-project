package technikumbackendfrontendproject.Backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.service.CartService;

import static org.springframework.http.HttpStatus.CREATED;

    
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ResponseStatus(code = CREATED)
    @PostMapping
    public Cart create(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @GetMapping("{userId}")
    public ResponseEntity<Cart> findCartById(@PathVariable Long userId) {
        Cart cart = cartService.findByUserId(userId);
        if(cart.getPositions().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(cart, HttpStatus.OK);
    }
}