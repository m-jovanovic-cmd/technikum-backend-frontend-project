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
import technikumbackendfrontendproject.Backend.model.DTO.LoginDTO;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;
import technikumbackendfrontendproject.Backend.service.AuthenticationService;
import technikumbackendfrontendproject.Backend.service.UserService;

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
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    private String userToken = "";
    private String adminToken = "";


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
        userToken = authenticationService.login(user.getUsername(), user.getPassword());
        adminToken = authenticationService.login(admin.getUsername(), admin.getPassword());
    }

    //Annotation -> JUnit ->  method is always executed after each test
    @AfterEach
    //no return (void) -> executed after each test method.
    void tearDown() {
        //remove all records from the database
        userRepository.deleteAll();

    }
    @Test
    void getAllUsersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        //        .header("Authorization", adminToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(2)))
                .andExpect(jsonPath("$.[*]").isNotEmpty());

    }



    @Test
    void getUserByWrongId() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/get/{id}", id)
                        .header("Authorization", adminToken))
                .andExpect(status().isNotFound());
    }
    @Test
    void getUserById() throws Exception {
        List<User> allUsers = assertDoesNotThrow(() -> userService.findAll());

        // Extract ID of first product from the list
        final Long userId = allUsers.stream().findFirst().get().getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/get/{id}", userId)
                        .header("Authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    /*


    *@Test
    void deleteUserRightIdTest() {
        User user11 = new User();
        user11.setId(1111L);
        user11.setAdmin(true);
        user11.setGender("Frau");
        user11.setUsername("adminname");
        user11.setPassword("adminpassword");
        user11.setFirstname("adminfirst");
        user11.setLastname("adminlast");
        user11.setEmail("user11@user11.at");
        user11.setPostcode("adminpostcode");
        user11.setLocation("adminlocation");
        user11.setStreet("1111");
        user11.setStreetnumber("admin12");
        user11.setStatus("adminstatus");
        user11.setRole("Admin");
        //final LoginDTO credentials = new LoginDTO(username, userpassword);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users"))
                .header("Authorization", "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user11))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(2)))
                .andExpect(jsonPath("$.[*]").isNotEmpty());
    }




    @Test
    void deleteUserWrongIdTest() {

    }
    @Test
    void updateUserWrongIdTest() {

    }

    @Test
    void updateUserIdTest() {

    }

     */
}
