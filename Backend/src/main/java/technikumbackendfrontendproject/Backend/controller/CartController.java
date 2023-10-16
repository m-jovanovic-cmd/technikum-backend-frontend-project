package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.model.DTO.CartDTO;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.service.CartService;
import technikumbackendfrontendproject.Backend.service.EntityNotFoundException;
import technikumbackendfrontendproject.Backend.service.ProductService;
import technikumbackendfrontendproject.Backend.service.UserService;

import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

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

    @PutMapping("/{userId}/{productId}/{isAdded}")
    //find cart with userId, jeder user hat nur eine cart
    public ResponseEntity<CartDTO> updateCartWithUserId(@PathVariable Long userId, @PathVariable Long productId, @PathVariable Boolean isAdded) {
        logger.info("Ich bin in Methode");
        User user = userService.findById(userId);
        logger.info("user:" + user.getUsername());
        logger.info("userId:" + userId);
        logger.info("isAdded:" + isAdded);

        Product product = productService.findById(productId);
        logger.info("Product: " + product.getName());
        try {
            //User user = userService.findById(userId);

            Cart workOnCart = cartService.checkIfCartIsExisting(user, product, isAdded);

            logger.info("Cart with userID: " + userId + "got updated or created!");
            return new ResponseEntity<>(workOnCart, HttpStatus.OK);
        } catch (EntityNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    //find cart with userId, jeder user hat nur eine cart
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long userID, @RequestBody CartDTO updatedCartDto) {
        try {
            CartDTO updatedCart = cartService.updateCart(userID, updatedCartDto);

            logger.info("Cart with userID: " + userID + " updated!");

            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (EntityNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }
}
