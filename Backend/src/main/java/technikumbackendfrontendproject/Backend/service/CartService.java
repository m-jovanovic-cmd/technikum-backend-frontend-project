package technikumbackendfrontendproject.Backend.service;

import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.repository.CartRepository;

import java.util.Optional;

@Service
public class CartService {

    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart findByUserId(Long userID) {
        return cartRepository.findByUserId(userID);
    }

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    public void deleteCart(Long id) {
        // Check if the cart exists
        Optional<Cart> userOptional = cartRepository.findById(id);
        if (userOptional.isPresent()) {
            cartRepository.delete(userOptional.get());
        } else {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
    }
    public Cart getCart(Long id) {
        var user = cartRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Cart cart2 = user.get();
        return cart2;
    }

}
