package technikumbackendfrontendproject.Backend.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.repository.CartRepository;

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
        try {
            return cartRepository.findByUserId(userID);
        } catch (ObjectNotFoundException e) {
            return null;
        }
    }

    // Test für git actions
    public Cart findByIdontKnow(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
