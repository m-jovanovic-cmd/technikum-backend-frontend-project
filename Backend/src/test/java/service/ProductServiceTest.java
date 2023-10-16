package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import technikumbackendfrontendproject.Backend.BackendApplication;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.Tax;
import technikumbackendfrontendproject.Backend.repository.ProductRepository;
import technikumbackendfrontendproject.Backend.repository.TaxRepository;
import technikumbackendfrontendproject.Backend.service.ProductService;

import java.util.List;

//BackendApplication = Main
@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        Product product1 = new Product();
        product1.setName("DropletDamian1");
        product1.setDescription("Test1");
        product1.setImageUrl("\\Frontend\\images\\products\\DropletDamian.jpg");
        product1.setPrice(1);
        product1.setQuantity(1);
        product1.setStatus(true);
        product1.setTax(new Tax());

        Product product2 = new Product();
        product1.setName("RainyRon2");
        product1.setDescription("Test2");
        product1.setImageUrl("\\Frontend\\images\\products\\RainyRon.jpg");
        product1.setPrice(2);
        product1.setQuantity(2);
        product1.setStatus(true);
        product1.setTax(new Tax());

        Product product3 = new Product();
        product1.setName("CharmingClaude3");
        product1.setDescription("Test3");
        product1.setImageUrl("\\Frontend\\images\\products\\CharmingClaude.jpg");
        product1.setPrice(3);
        product1.setQuantity(3);
        product1.setStatus(true);
        product1.setTax(new Tax());

        productRepository.saveAll(List.of(product1, product2, product3));


    }
    @Test
    void findAllTest() {

    }
}
