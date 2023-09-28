package service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import technikumbackendfrontendproject.Backend.BackendApplication;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class ProductServiceTest {

}

/*
import com.webshop.webshop.WebshopApplication;
        import com.webshop.webshop.enums.ProductCategory;
        import com.webshop.webshop.model.Product;
        import com.webshop.webshop.repository.ProductRepository;
        import com.webshop.webshop.service.ProductService;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.ActiveProfiles;

        import static org.junit.Assert.assertEquals;


@SpringBootTest(classes = WebshopApplication.class)
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {

    }

    @Test
    void saveTest() {
        assertEquals(1, 1);
    }
}
*/