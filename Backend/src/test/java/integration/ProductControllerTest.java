package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import technikumbackendfrontendproject.Backend.BackendApplication;
import technikumbackendfrontendproject.Backend.controller.TaxController;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.Tax;
import technikumbackendfrontendproject.Backend.repository.ProductRepository;
import technikumbackendfrontendproject.Backend.repository.TaxRepository;
import technikumbackendfrontendproject.Backend.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = BackendApplication.class)
@Disabled
@WebMvcTest
@ActiveProfiles("test")
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@ComponentScan("technikumbackendfrontendproject.Backend.controller")
@ComponentScan("technikumbackendfrontendproject.Backend.service")
@ComponentScan("technikumbackendfrontendproject.Backend.repository")
@ComponentScan("technikumbackendfrontendproject.Backend.config")
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TaxRepository taxRepository;

    @BeforeEach
    void setup() {
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

        Product product2 = new Product();
        product2.setName("RainyRon2");
        product2.setDescription("Test2");
        product2.setImageUrl("\\Frontend\\images\\products\\RainyRon.jpg");
        product2.setPrice(2);
        product2.setQuantity(2);
        product2.setType("Rare");
        product2.setStatus(true);
        product2.setTax(tax);

        Product product3 = new Product();
        product3.setName("CharmingClaude3");
        product3.setDescription("Test3");
        product3.setImageUrl("\\Frontend\\images\\products\\CharmingClaude.jpg");
        product3.setPrice(3);
        product3.setQuantity(3);
        product3.setType("Epic");
        product3.setStatus(true);
        product3.setTax(tax);


        productRepository.saveAll(List.of(product1, product2, product3));

    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();

    }

    @Test
    void findAllProductsTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(3)))
                .andExpect(jsonPath("$.[*]").isNotEmpty());

    }

    @Test
    void findByTypeTest() throws Exception {
        String productType = "Rare";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{type}",productType))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(1)))
                .andExpect(jsonPath("$.[*]").isNotEmpty());

    }
    @Test
    void findByWrongTypeTest() throws Exception {
        String productType = "DoesNotExistType";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{type}",productType))
                .andExpect(jsonPath("$.length()", Matchers.is(0)));
    }

    @Test
    void findByIdTest() throws Exception {
        // Get list of all products from ProductService -> no exceptions are thrown
        List<Product> allProducts = assertDoesNotThrow(() -> productService.findAll());

        // Extract ID of first product from the list
        final Long productId = allProducts.stream().findFirst().get().getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/details/{id}",productId))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isNotEmpty());

    }

    @Test
    void findByWrongIdTest() throws Exception {
        Long wrongId = 99999999L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/details/{id}",wrongId))
                .andExpect(status().isNotFound());

    }

}
