package unitServiceTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import technikumbackendfrontendproject.Backend.BackendApplication;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;
import technikumbackendfrontendproject.Backend.security.UserPrincipal;
import technikumbackendfrontendproject.Backend.service.AuthenticationService;
import technikumbackendfrontendproject.Backend.service.TokenService;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class TokenServiceTest {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    private AuthenticationService authenticationService;

    //executed before each test method
    @BeforeEach

        void setup(){
            User user1 = new User("weiblich", false,"username", "passwort", "firstname", "lastname", "mail@mail.at", "1111", "Lustenau", "Straße", "2", "Aktiv", "User" );
            User user2 = new User("männlich", false, "user123", "mypassword123", "John", "Doe", "johndoe@email.com", "1234", "New York", "Main Street", "5", "Aktiv", "User");
            User user3 = new User("weiblich", false, "inactiveuser", "securepass", "Alice", "Johnson", "alice@email.com", "5678", "Los Angeles", "Park Avenue", "10", "Inaktiv", "User");
            User user4 = new User("männlich", true, "admin", "adminpass123", "Admin", "Smith", "admin@admin.com", "4321", "Chicago", "Admin Street", "1", "Aktiv", "Administrator");
            //The userRepository is being used to save these User objects -> saved into database to save and retrieve data later
            userRepository.saveAll(List.of(user1, user2, user3, user4));
        }

        //Annotation -> JUnit ->  method is always executed after each test
        @AfterEach
        //no return (void) -> executed after each test method.
        void tearDown() {
            //remove all records from the database
            userRepository.deleteAll();

        }
    //generate Token
    //parse Token
    @Test
    void parseTokenTest() {
        final User customer = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals("username"))
                .findFirst()
                .get();
        final String customerToken = assertDoesNotThrow(() -> tokenService.generateToken(customer));
        Optional<UserPrincipal> var = assertDoesNotThrow(() -> tokenService.parseToken(customerToken));
        UserPrincipal userDetails = assertDoesNotThrow(() -> var.get());

        /*assertAll(
                () -> assertEquals(customer.getId(), userDetails.getUserID()),
                () -> assertEquals(customer.getUsername(), userDetails.getUsername()),
                () -> assertEquals(customer.getRole(), userDetails.getClass())
        );*/

    }


    @Test
    void isAdminTest() {
        
        // Find an admin user in the UserRepository and generate a token for them.
        final User admin = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals("admin"))
                .findFirst()
                .get();
        final String adminToken = assertDoesNotThrow(() -> tokenService.generateToken(admin));

        // Parse the token and assert that the admin user is indeed an admin.
        Optional<UserPrincipal> var = assertDoesNotThrow(() -> tokenService.parseToken(adminToken));
        assertTrue(admin.isAdmin());
    }

    @Test
    void isNotAdminTest() {
        // Find a customer user in the UserRepository and generate a token for them.
        final User customer = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals("username"))
                .findFirst()
                .get();
        final String customerToken = assertDoesNotThrow(() -> tokenService.generateToken(customer));

        // Parse the token and ensure that the customer is not an admin.
        Optional<UserPrincipal> var = assertDoesNotThrow(() -> tokenService.parseToken(customerToken));
        assertFalse(customer.isAdmin());

    }

}

