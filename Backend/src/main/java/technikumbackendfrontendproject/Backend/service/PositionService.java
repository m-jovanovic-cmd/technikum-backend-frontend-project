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
    private final UserRepository userRepositiory;

    private final CartService cartService;
    private final ProductService productService;


    public PositionService(PositionRepository positionRepository, UserRepository userRepositiory, CartService cartService, ProductService productService) {
        this.positionRepository = positionRepository;
        this.cartService = cartService;
        this.userRepositiory = userRepositiory;
        this.productService = productService;
    }

    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    public Position save(Position position, Long userId, Long productId) {
        Cart cart = cartService.findByUserId(userId);

        if (cart == null)  {
            Optional<User> user = userRepositiory.findById(userId);

            if (user.isPresent()) {
                cart = cartService.save(new Cart(user.get()));
            } else {
                throw new RuntimeException("User does not exist");
            }
        }

        Optional<Product> product = productService.findById(productId);

        if (product.isEmpty()) {
            throw new RuntimeException("Product does not exist");
        }

        position.setCart(cart);
        position.setProduct(product.get());

        return positionRepository.save(position);
    }
}