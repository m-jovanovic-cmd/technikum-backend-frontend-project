package technikumbackendfrontendproject.Backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.service.CartService;
import technikumbackendfrontendproject.Backend.service.PositionService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;
    
    public PositionController(PositionService positionService, CartService cartService) {
        this.positionService = positionService;
    }

    @PostMapping("/{userId}/{productId}")
    @ResponseStatus(code = CREATED)
    public ResponseEntity<String> createPosition(@PathVariable Long userId, @PathVariable Long productId) {
        positionService.addOneProductToCart(userId, productId);
        return new ResponseEntity<>("Produkt dem Warenkorb hinzugefügt", HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{productId}")
    public ResponseEntity<String> removePosition(@PathVariable Long userId, @PathVariable Long productId) {
        HttpStatus status = positionService.removeOneProductFromCart(userId, productId);
        return new ResponseEntity<>("Produkt dem Warenkorb hinzugefügt", status);
    }
}
