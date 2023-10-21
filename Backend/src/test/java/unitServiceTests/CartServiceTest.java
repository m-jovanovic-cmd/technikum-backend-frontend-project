package unitServiceTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import technikumbackendfrontendproject.Backend.BackendApplication;
import technikumbackendfrontendproject.Backend.model.*;
import technikumbackendfrontendproject.Backend.repository.*;
import technikumbackendfrontendproject.Backend.service.CartService;
import technikumbackendfrontendproject.Backend.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//2

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class CartServiceTest {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;


    @BeforeEach
    void setup(){
        //id, cart_id, product_id, quantity
        Tax tax = new Tax();
        tax.setName("Testax");
        tax.setFactor(2.0);
        taxRepository.save(tax);

        Product product1 = new Product();
        product1.setName("DropletDamian1");
        product1.setDescription("Test1");
        product1.setImageUrl("\\Frontend\\images\\products\\DropletDamian.jpg");
        product1.setPrice(1);
        product1.setQuantity(1);
        product1.setType("Ultima");
        product1.setStatus(true);
        product1.setTax(tax);

        productRepository.save(product1);

        User user4 = new User("männlich", true, "admin", "adminpass123", "Admin", "Smith", "admin@admin.com", "4321", "Chicago", "Admin Street", "1", "Aktiv", "Administrator");
        userRepository.save(user4);

        Cart cart = new Cart();
        //cart.setUser(user4);
        cart.setTotal(200.0);
        cartRepository.save(cart);

    }


    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        taxRepository.deleteAll();
        userRepository.deleteAll();
        positionRepository.deleteAll();
    }


    @Test
    void findByUserIdTest(){

        List<User> allUsers = assertDoesNotThrow(() -> userRepository.findAll());
        // Extract ID of first user from list
        final Long userId = allUsers.stream().findFirst().get().getId();
        User user = assertDoesNotThrow(() -> userService.findById(userId));
        Cart cart= assertDoesNotThrow(() -> cartRepository.findByUserId(userId));

    }

    @Test
    void findByWrongUserIdTest(){
        Long wrongId = 992222L;
        List<Cart> carts = (List<Cart>) cartRepository.findByUserId(wrongId);
        // Check if carts is null or empty
        if (carts == null || carts.isEmpty()) {
            // Handle the case where carts is null or empty (no results found)
        } else {
            fail("Expected no results, but found " + carts.size() + " results.");
        }

    }

}
