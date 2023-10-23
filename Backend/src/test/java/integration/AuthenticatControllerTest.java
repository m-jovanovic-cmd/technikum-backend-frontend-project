package integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import technikumbackendfrontendproject.Backend.BackendApplication;
import technikumbackendfrontendproject.Backend.model.DTO.LoginDTO;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;
import technikumbackendfrontendproject.Backend.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@WebMvcTest
@ActiveProfiles("test")
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@ComponentScan("technikumbackendfrontendproject.Backend.controller")
@ComponentScan("technikumbackendfrontendproject.Backend.service")
@ComponentScan("technikumbackendfrontendproject.Backend.repository")
@ComponentScan("technikumbackendfrontendproject.Backend.config")
@ContextConfiguration(classes = BackendApplication.class)
//@SpringBootTest(classes = BackendApplication.class)
public class AuthenticatControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    //Annotation ->setup method -> runs before each test run
    //sets up initial data before each test
    @BeforeEach
    //void -> does not return anything
    //executed before each test method

    void setup(){

        User user = new User();
        user.setId(999L);
        user.setAdmin(false);
        user.setGender("Frau");
        user.setUsername("username");
        user.setPassword("userpassword");
        user.setFirstname("userfirst");
        user.setLastname("userlast");
        user.setEmail("user@user.at");
        user.setPostcode("userpostcode");
        user.setLocation("userlocation");
        user.setStreet("userStreet");
        user.setStreetnumber("1111");
        user.setStatus("userstatus");
        user.setRole("Customer");

        User admin = new User();
        user.setId(888L);
        admin.setAdmin(true);
        admin.setGender("Frau");
        admin.setUsername("adminname");
        admin.setPassword("adminpassword");
        admin.setFirstname("adminfirst");
        admin.setLastname("adminlast");
        admin.setEmail("admin@admin.at");
        admin.setPostcode("adminpostcode");
        admin.setLocation("adminlocation");
        admin.setStreet("1111");
        admin.setStreetnumber("admin12");
        admin.setStatus("adminstatus");
        admin.setRole("Admin");


        //The userRepository is being used to save these User objects -> saved into database to save and retrieve data later
        userRepository.save(user);
        userRepository.save(admin);
    }

    //Annotation -> JUnit ->  method is always executed after each test
    @AfterEach
    //no return (void) -> executed after each test method.
    void tearDown() {
        //remove all records from the database
        userRepository.deleteAll();

    }

    @Test
    void loginTest() throws Exception {
        final String username = "username";
        final String userpassword = "userpassword";
        final LoginDTO credentials = new LoginDTO(username, userpassword);
       String response = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .content(mapper.writeValueAsString(credentials))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andReturn().getResponse().getContentAsString();
       assertEquals("Bearer ", response.substring(0,7));
        //check if string starts with bearer
        //check length

    }

    @Test
    void isNotAdminTest() throws Exception {
        final String username = "username";
        final String userpassword = "userpassword";
        final LoginDTO credentials = new LoginDTO(username, userpassword);
        mockMvc.perform(MockMvcRequestBuilders.post("/isadmin")
                        .content(mapper.writeValueAsString(credentials))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();
    }
}
