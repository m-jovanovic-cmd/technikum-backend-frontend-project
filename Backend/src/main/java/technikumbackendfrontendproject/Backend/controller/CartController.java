package technikumbackendfrontendproject.Backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.service.CartService;

import static org.springframework.http.HttpStatus.CREATED;

    
@RestController
@RequestMapping("/api")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ResponseStatus(code = CREATED)
    @PostMapping("/carts")
    public Cart create(@RequestBody Cart cart) {
        return cartService.save(cart);
    }
}