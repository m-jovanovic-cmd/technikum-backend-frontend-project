package technikumbackendfrontendproject.Backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import technikumbackendfrontendproject.Backend.security.UserPrincipal;
import technikumbackendfrontendproject.Backend.service.CartService;
import technikumbackendfrontendproject.Backend.service.PositionService;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;
    
    public PositionController(PositionService positionService, CartService cartService) {
        this.positionService = positionService;
    }

    @PostMapping("/{productId}")
    @ResponseStatus(code = CREATED)
    public ResponseEntity<String> createPosition(@AuthenticationPrincipal Optional<UserPrincipal> user, @PathVariable Long productId) {
        positionService.addOneProductToCart(user, productId);
        return new ResponseEntity<>("Produkt dem Warenkorb hinzugefügt", HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> removePosition(@AuthenticationPrincipal Optional<UserPrincipal> user, @PathVariable Long productId) {
        HttpStatus status = positionService.removeOneProductFromCart(user, productId);
        return new ResponseEntity<>("Produkt dem Warenkorb hinzugefügt", status);
    }
}
