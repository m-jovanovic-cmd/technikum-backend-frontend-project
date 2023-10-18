package technikumbackendfrontendproject.Backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.Cart;
import technikumbackendfrontendproject.Backend.model.Position;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.PositionRepository;
import technikumbackendfrontendproject.Backend.repository.ProductRepository;
import technikumbackendfrontendproject.Backend.repository.UserRepository;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final CartService cartService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PositionService(PositionRepository positionRepository, CartService cartService, UserRepository userRepository, ProductRepository productRepository) {
        this.positionRepository = positionRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    public HttpStatus addOneProductToCart(Long userId, Long productId) {
        Cart cart = cartService.findByUserId(userId);
        Optional<User> user = userRepository.findById(userId);
        Optional<Product> product = productRepository.findById(productId);

        if(cart == null) {
            if(user.isPresent() && product.isPresent()) {
                Cart newCart = cartService.save(new Cart(user.get()));
                newCart.setTotal(product.get().getPrice());
                Position position = new Position(newCart, product.get(), 1);
                positionRepository.save(position);
                return HttpStatus.CREATED;
            } else {
                return HttpStatus.NOT_FOUND;
            }
        } else {
            if(user.isPresent() && product.isPresent()) {
                List<Position> produktListe = findAllPositionsByCartId(cart.getId());
                Position cartPosition = produktListe.stream()
                        .filter(position -> position.getProduct().getId().equals(productId))
                        .findFirst()
                        .orElse(null);

                cart.setTotal(cart.getTotal() + product.get().getPrice());
                if (cartPosition == null) {
                    Position position = new Position(cart, product.get(), 1);
                    positionRepository.save(position);
                    return HttpStatus.CREATED;
                } else {
                    cartPosition.setQuantity(cartPosition.getQuantity() + 1);
                    positionRepository.save(cartPosition);
                    return HttpStatus.OK;
                }
            }
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus removeOneProductFromCart(Long userId, Long productId) {
        Cart cart = cartService.findByUserId(userId);
        Optional<User> user = userRepository.findById(userId);
        Optional<Product> product = productRepository.findById(productId);

        if(cart != null && user.isPresent() && product.isPresent()) {
            List<Position> produktListe = findAllPositionsByCartId(cart.getId());
            Position cartPosition = produktListe.stream()
                    .filter(position -> position.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);
            if (cartPosition == null) {
                return HttpStatus.NOT_FOUND;
            } else if (cartPosition.getQuantity() <= 1){
                positionRepository.delete(cartPosition);
                return HttpStatus.OK;
            } else {
                cartPosition.setQuantity(cartPosition.getQuantity() - 1);
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

    public List<Position> findAllPositionsByCartId(Long cartId) {
        return positionRepository.findAllByCart_Id(cartId);
    }
}