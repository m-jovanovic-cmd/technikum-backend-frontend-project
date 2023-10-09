package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.repository.ProductRepository;
import technikumbackendfrontendproject.Backend.repository.TaxRepository;
import technikumbackendfrontendproject.Backend.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    
    private ProductService productService;
    
    @Mock
    private ProductRepository productRepository;

    @Mock
    private TaxRepository taxRepository;

    @BeforeEach
    public void setup() {
        productService = new ProductService(productRepository, taxRepository);
    }

    @Test
    public void whenListIsEmpty_ThenFalse() {
        List<Product> dummy_products_list = new ArrayList<>();
        dummy_products_list.add(new Product());
        dummy_products_list.add(new Product());
        dummy_products_list.add(new Product());

        when(productRepository.findAll()).thenReturn(dummy_products_list);

        List <Product> result_list = productService.findAll();
        assertEquals(dummy_products_list, result_list);
        verify(productRepository, times(1)).findAll();
    }
}