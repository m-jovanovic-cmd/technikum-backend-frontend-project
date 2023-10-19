package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.model.DTO.CartModalDTO;
import technikumbackendfrontendproject.Backend.model.DTO.CartProductDTO;
import technikumbackendfrontendproject.Backend.model.Position;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.security.UserPrincipal;
import technikumbackendfrontendproject.Backend.service.CartService;
import technikumbackendfrontendproject.Backend.service.PositionService;
import technikumbackendfrontendproject.Backend.service.ProductService;
import technikumbackendfrontendproject.Backend.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

    
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @ResponseStatus(code = CREATED)
    @PostMapping
    public Cart create(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> findCartById(@PathVariable Long userId) {
        Cart cart = cartService.findByUserId(userId);
        if(cart.getPositions().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/getCart")
    public CartModalDTO getAllProductsFromUser(@AuthenticationPrincipal Optional<UserPrincipal> user) {
        Cart cart = cartService.findByUserId(user.get().getUserID());
        if(cart != null) {
            List<Position> positions = positionService.findAllPositionsByCartId(cart.getId());
            List<CartProductDTO> products = new ArrayList<>();
            for(Position position: positions) {
                Product product = position.getProduct();
                products.add(new CartProductDTO(product.getId(), product.getName(), product.getPrice(), position.getQuantity()));
            }
            return new CartModalDTO(products, cart.getTotal());
        }
        return null;
    }
}