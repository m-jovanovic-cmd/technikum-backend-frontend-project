package service;

import org.junit.jupiter.api.AfterEach;
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
import technikumbackendfrontendproject.Backend.service.EntityNotFoundException;
import technikumbackendfrontendproject.Backend.service.ProductService;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

//BackendApplication = Main
@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TaxRepository taxRepository;

    @BeforeEach
    void setup() {

        Product product1 = new Product();
        product1.setName("DropletDamian1");
        product1.setDescription("Test1");
        product1.setImageUrl("\\Frontend\\images\\products\\DropletDamian.jpg");
        product1.setPrice(1);
        product1.setQuantity(1);
        product1.setType("Ultima");
        product1.setStatus(true);

        Product product2 = new Product();
        product2.setName("RainyRon2");
        product2.setDescription("Test2");
        product2.setImageUrl("\\Frontend\\images\\products\\RainyRon.jpg");
        product2.setPrice(2);
        product2.setQuantity(2);
        product2.setType("Rare");
        product2.setStatus(true);

        Product product3 = new Product();
        product3.setName("CharmingClaude3");
        product3.setDescription("Test3");
        product3.setImageUrl("\\Frontend\\images\\products\\CharmingClaude.jpg");
        product3.setPrice(3);
        product3.setQuantity(3);
        product3.setType("Epic");
        product3.setStatus(true);

        productRepository.saveAll(List.of(product1, product2, product3));

    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();

    }

    // Unit Test for findAllAll Method
    @Test
    void findAllTest() {
        // checks if any Errors are thrown
        List<Product> allProducts = assertDoesNotThrow(() -> productService.findAll());

        // check if List is not Null
        assertNotNull(allProducts);

        // checks size of list
        assertEquals(3, allProducts.size());

    }

    @Test
    void findByIdTest() {

        List<Product> allProducts = assertDoesNotThrow(() -> productService.findAll());

        final Long productId = allProducts.stream().findFirst().get().getId();
        final Long wrongId = 999999L;
        Product product = assertDoesNotThrow(() -> productService.findById(productId));
        assertThrows(RuntimeException.class,
                () -> productService.findById(wrongId));

    }

    @Test
    void findByWrongIdTest() {

        List<Product> allProducts = assertDoesNotThrow(() -> productService.findAll());

        final Long productId = allProducts.stream().findFirst().get().getId();
        final Long wrongId = 999999L;
        Product product = assertDoesNotThrow(() -> productService.findById(productId));
        assertThrows(RuntimeException.class,
                () -> productService.findById(wrongId));

    }

    // findByType
    @Test
    void findByTypeTest() {
        String productType = "Ultima";
        List<Product> typeProducts = productService.findByType(productType);
        System.out.println(typeProducts);

        assertEquals(1, typeProducts.size());

        Product firstProduct = typeProducts.get(0);
        //System.out.println(firstProduct);
        assertEquals("Ultima", firstProduct.getType());
    }

    @Test
    void findByWrongTypeTest() {
        String productType = "abc";
        List<Product> typeProducts = productService.findByType(productType);
        System.out.println(typeProducts);

        assertEquals(0, typeProducts.size());

    }
    @Test
    void saveWithValidTaxTest(){
        Tax tax = new Tax(11L, "Tax", 2.0);
        taxRepository.save(tax);
        List<Tax> taxes = taxRepository.findAll();
        assertEquals(1, taxes.size());
        taxRepository.deleteAll();

    }
    // save
    @Test
    void saveNotNullTest() {
        Tax tax = new Tax();
        taxRepository.save(tax);

        Product product = new Product();
        product.setName("CharmingClaude3");
        product.setDescription("Test3");
        product.setImageUrl("\\Frontend\\images\\products\\CharmingClaude.jpg");
        product.setPrice(3);
        product.setQuantity(3);
        product.setType("Epic");
        product.setStatus(true);
        product.setTax(tax);

        productService.save(product);
        List<Product> products = productRepository.findAll();
        assertNotNull(products);
        productRepository.deleteAll();

    }
    @Test
    void saveCorrectlyProductTest() {
        List<Product> products = productRepository.findAll();
        assertEquals(3, products.size());

        Tax tax = new Tax();
        taxRepository.save(tax);

        Product product = new Product();
        product.setName("CharmingClaude3");
        product.setDescription("Test3");
        product.setImageUrl("\\Frontend\\images\\products\\CharmingClaude.jpg");
        product.setPrice(3);
        product.setQuantity(3);
        product.setType("Epic");
        product.setStatus(true);
        product.setTax(tax);

        productService.save(product);
        List<Product> productsAdded = productRepository.findAll();
        assertEquals(4, productsAdded.size());
        productRepository.deleteAll();

    }


}
