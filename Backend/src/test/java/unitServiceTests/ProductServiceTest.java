package unitServiceTests;

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
import technikumbackendfrontendproject.Backend.service.ProductService;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
//7
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
        // Get list of all products from ProductService -> no exceptions are thrown
        List<Product> allProducts = assertDoesNotThrow(() -> productService.findAll());

        // Extract ID of first product from the list
        final Long productId = allProducts.stream().findFirst().get().getId();

        // Retrieve a product with valid ID -> ensuring no exceptions are thrown
        Optional<Product> product = assertDoesNotThrow(() -> productService.findById(productId));

    }



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
        Tax tax1 = new Tax(111111L, "Taxx", 22.0);
        taxRepository.save(tax1);
        List<Tax> taxes = taxRepository.findAll();
        assertEquals(2, taxes.size());
        //taxRepository.deleteAll();

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
        // Get the list of products before the test to ensure its initial size
        List<Product> products = productRepository.findAll();
        assertEquals(3, products.size());

        // Create a new Tax object and save it to be associated with the product
        Tax tax = new Tax();
        taxRepository.save(tax);

        // Create a new product
        Product product = new Product();
        product.setName("CharmingClaude3");
        product.setDescription("Test3");
        product.setImageUrl("\\Frontend\\images\\products\\CharmingClaude.jpg");
        product.setPrice(3);
        product.setQuantity(3);
        product.setType("Epic");
        product.setStatus(true);
        product.setTax(tax);

        // Save the new product  in ProductService
        productService.save(product);

        // Retrieve the list of products again after saving and check if the size has increased by one
        List<Product> productsAdded = productRepository.findAll();
        assertEquals(4, productsAdded.size());

        // Clean up by deleting the newly added product to restore the original state
        productRepository.deleteAll();
    }

    /*
    *@Test
    void findByWrongIdTest() {
        // Get list of all products from ProductService -> no exceptions are thrown
        List<Product> allProducts = assertDoesNotThrow(() -> productService.findAll());

        // Get ID of first product from the list
        final Long productId = allProducts.stream().findFirst().get().getId();

        // Define invalid product ID
        final Long wrongId = 999999L;

        // Ensure that calling 'findById' with the invalid product ID throws a RuntimeException
        Optional<Product> product = assertDoesNotThrow(() -> productService.findById(productId));
        assertThrows(Exception.class,
                () -> productService.findById(wrongId));

    }
     */


}
