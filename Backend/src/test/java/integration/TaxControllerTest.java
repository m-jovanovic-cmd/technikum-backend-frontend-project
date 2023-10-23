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
import technikumbackendfrontendproject.Backend.model.Tax;
import technikumbackendfrontendproject.Backend.repository.TaxRepository;

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
public class TaxControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private TaxController taxController;

    @BeforeEach
    void setup(){
        Tax tax = new Tax();
        tax.setFactor(2.22);
        tax.setName("Luxussteuer");
        taxRepository.save(tax);
    }

    @AfterEach
    void tearDown() {
        taxRepository.deleteAll();

    }

    @Test
    void getAllTaxTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/taxes")
                        //        .header("Authorization", adminToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(1)))
                .andExpect(jsonPath("$.[*]").isNotEmpty());
    }

    @Test
    void createTaxTest() throws Exception {
        // Create a Tax object to be saved
        Tax newTax = new Tax();
        newTax.setFactor(2.22);
        newTax.setName("Luxussteuer");

        // Mock the behavior of taxRepository.save to return the savedTax

        mockMvc.perform(MockMvcRequestBuilders.post("/api/taxes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newTax))
                        .content(mapper.writeValueAsString(newTax)))
                .andExpect(status().isCreated());
    }

    @Test
    void createTaxWithWrongDataTest() throws Exception {
        // Create a Tax object to be saved (let's say this is a "wrong" Tax)
        String wrongTax = "invalid";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/taxes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(wrongTax)))
                .andExpect(status().isBadRequest()); // Expect HTTP 400 (BAD REQUEST) status
    }
}








