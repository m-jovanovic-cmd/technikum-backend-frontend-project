package technikumbackendfrontendproject.Backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.model.Position;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.PositionRepository;
import technikumbackendfrontendproject.Backend.repository.UserRepository;

@Service
public class PositionService {
    
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;

    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;


    public PositionService(PositionRepository positionRepository, UserRepository userRepository, UserService userService, CartService cartService, ProductService productService) {
        this.positionRepository = positionRepository;
        this.userService = userService;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    public Position save(Position position, Long userId, Long productId) {
        Cart cart = cartService.findByUserId(userId);

        if (cart == null)  {
            Optional<User> user = userRepository.findById(userId);

            if (user.isPresent()) {
                cart = cartService.save(new Cart(user.get()));
            } else {
                throw new RuntimeException("User does not exist");
            }
        }

        Product product = productService.findById(productId);

        position.setCart(cart);
        position.setProduct(product);

        return positionRepository.save(position);
    }

    public Position create(Long userId, Long productId) {
       User user = userService.findById(userId);
       Product product = productService.findById(productId);
       // This is faster :)
       Cart cart = user.getCart();
       // Cart cart = cartService.findByUserId(userId);

        Position position = new Position();
        position.setCart(cart);
        position.setProduct(product);
        position.setQuantity(1);

        return positionRepository.save(position);
    }

    public Position update(Position position, Boolean isAdded) {
        Integer quantity = position.getQuantity();
        if (isAdded) {
            position.setQuantity(quantity + 1);
        } else if (quantity <= 0) {
            throw new IllegalArgumentException("Product quantity cannot be: " + quantity);
        } else if (quantity == 1){
            positionRepository.delete(position);
            return null;
        } else {
            position.setQuantity(quantity - 1);
        }
        return positionRepository.save(position);
    }


}