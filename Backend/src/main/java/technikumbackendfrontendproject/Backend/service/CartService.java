package technikumbackendfrontendproject.Backend.service;

import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.model.DTO.CartDTO;
import technikumbackendfrontendproject.Backend.repository.CartRepository;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CartService {

    private CartRepository cartRepository;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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

    public CartDTO updateCart(Long existingCartID, CartDTO updatedCartDto) {
        var existingCart = cartRepository.findById(existingCartID);

        if (existingCart.isEmpty()) {
            throw new EntityNotFoundException("User with id: " + existingCartID + " does not exist!");
        } else {
            Cart updatedCart = existingCart.get();

            // Update the user information with values from the DTO
            logger.info("Updating TOTAL: " + updatedCart.getTotal() + " -> " + updatedCartDto.getTotal());
            updatedCart.setTotal(updatedCartDto.getTotal());
            logger.info("Updating ORDERSTATUS: " + updatedCart.getOrderstatus() + " -> " + updatedCartDto.getOrderstatus());
            updatedCart.setOrderstatus(updatedCartDto.getOrderstatus());
            logger.info("Updating POSITION: " + updatedCart.getPositions() + " -> " + updatedCartDto.getPositions());
            updatedCart.setPositions(updatedCartDto.getPositions());
            //use this, to change user-id from unlooged to logged in user?
            logger.info("Updating USER-ID: " + updatedCart.getUser() + " -> " + updatedCartDto.getUser());
            updatedCart.setUser(updatedCartDto.getUser());
            logger.info("Updating PRODUCT: " + updatedCart.getProduct() + " -> " + updatedCartDto.getProduct());
            updatedCart.setProduct(updatedCartDto.getProduct());

            // Save the updated user
            cartRepository.save(updatedCart);
            return convertToCartDto(updatedCart);
        }
    }
    // Utility method to convert a User entity to UserDto
    public CartDTO convertToCartDto(Cart cart) {
        CartDTO cartDto = new CartDTO();
        cartDto.setTotal(cart.getTotal());
        cartDto.setOrderstatus((cart.getOrderstatus()));
        cartDto.setPositions(cart.getPositions());
        cartDto.setUser(cart.getUser());
        cartDto.setProduct(cart.getProduct());
        return cartDto;
    }

}
