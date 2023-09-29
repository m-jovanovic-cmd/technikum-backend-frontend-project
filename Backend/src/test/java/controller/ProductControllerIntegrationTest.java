/*package controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import technikumbackendfrontendproject.Backend.config.SecurityConfig;
import technikumbackendfrontendproject.Backend.controller.ProductController;
import technikumbackendfrontendproject.Backend.model.DTO.ProductDTO;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.repository.ProductRepository;
import technikumbackendfrontendproject.Backend.repository.TaxRepository;
import technikumbackendfrontendproject.Backend.service.ProductService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ExtendWith(SpringExtension.class)
@Import({ProductService.class, SecurityConfig.class})
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private TaxRepository taxRepository;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void ShouldCreateProductAsAdmin() throws Exception {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setDescription("Blabla");
        productDTO.setImageUrl("imageUrl");
        productDTO.setStatus(true);
        productDTO.setName("Name");
        productDTO.setPrice(200);
        productDTO.setQuantity(12);
        productDTO.setType("Basic");
        productDTO.setTaxId(2L);

        Product product = new Product();
        Gson gson = new Gson();

        when(productRepository.save(any())).thenReturn(product);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/products")
                .content(gson.toJson(productDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(result -> gson.toJson(product));
    }
}*/