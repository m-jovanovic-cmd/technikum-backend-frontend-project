package technikumbackendfrontendproject.Backend.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.model.DTO.CartDTO;
import technikumbackendfrontendproject.Backend.model.Position;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.CartRepository;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CartService {
    private PositionService positionService;
    private CartRepository cartRepository;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart createCart(User user) {

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
            return cart;
    }

    public Cart checkIfCartIsExisting(User user, Product product, Boolean isAdded) {
        Cart usercart = user.getCart();


        if (usercart == null) {
            usercart = createCart(user);
           // Position position = positionService.create(user.getId(), product.getId());
            Position position = positionService.create(user.getId(), product.getId());

        } else {
            Position position = positionService.findByUserId(user.getId());
            positionService.update(position, isAdded);
            }
        return usercart;
    }


    public Cart findByUserId(Long userID) {
        try {
            return cartRepository.findByUserId(userID).get();
        } catch(ObjectNotFoundException e) {
            throw new RuntimeException("no cart for this id: "+ userID);
        }

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
            logger.info("Updating TOTAL: " + updatedCart.getTotal() + " -> " + updatedCartDto.getTotal()+updatedCartDto.getTotal());
            updatedCart.setTotal(updatedCartDto.getTotal()+updatedCartDto.getTotal());
            logger.info("Updating POSITION: " + updatedCart.getPositions() + " -> " + updatedCartDto.getPositions());
            updatedCart.setPositions(updatedCartDto.getPositions());
            //use this, to change user-id from unlooged to logged in user?
            logger.info("Updating USER-ID: " + updatedCart.getUser() + " -> " + updatedCartDto.getUser());
            updatedCart.setUser(updatedCartDto.getUser());

            // Save the updated user
            cartRepository.save(updatedCart);
            return convertToCartDto(updatedCart);
        }
    }
    // Utility method to convert a User entity to UserDto
    public CartDTO convertToCartDto(Cart cart) {
        CartDTO cartDto = new CartDTO();
        cartDto.setTotal(cart.getTotal());
        cartDto.setPositions(cart.getPositions());
        cartDto.setUser(cart.getUser());
        return cartDto;
    }

}
