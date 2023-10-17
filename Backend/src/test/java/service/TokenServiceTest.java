package service;

import junit.framework.TestCase;
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
import static org.mockito.Mockito.when;

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
    void setup() {
        User customer = new User();
        customer.setId(1L);
        customer.setUsername("customer");
        customer.setPassword("customerPassword");
        customer.setEmail("customer@email.com");
        customer.setRole("Customer");
        customer.setGender("female");
        customer.setAdmin(false);

        customer.setFirstName("customerFirst");
        customer.setLastName("customerLast");
        User admin = new User();
        admin.setId(2L);
        admin.setUsername("admin");
        admin.setPassword("adminPassword");
        admin.setEmail("admin@email.com");
        admin.setRole("Admin");
        admin.setAdmin(true);
        admin.setGender("male");
        admin.setFirstName("adminFirst");
        admin.setLastName("adminLast");

        User user3 = new User("weiblich", "inactiveuser", "securepass", "Alice", "Johnson", "alice@email.com", "5678", "Los Angeles", "Park Avenue", "10", "Inaktiv", "User");
        user3.setAdmin(false);
        User user4 = new User("männlich", "admin", "adminpass123", "Admin", "Smith", "admin@admin.com", "4321", "Chicago", "Admin Street", "1", "Aktiv", "Administrator");
        user4.setAdmin(true);
        userRepository.saveAll(List.of(customer, admin, user3, user4));
    }


    //generate Token
    //parse Token
    @Test
    void parseTokenTest() {
        final User customer = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals("customer"))
                .findFirst()
                .get();
        final String customerToken = assertDoesNotThrow(() -> tokenService.generateToken(customer));
        Optional<UserPrincipal> var = assertDoesNotThrow(() -> tokenService.parseToken(customerToken));
        UserPrincipal userDetails = assertDoesNotThrow(() -> var.get());
        assertAll(
                () -> assertEquals(customer.getId(), userDetails.getUserID()),
                () -> assertEquals(customer.getUsername(), userDetails.getUsername()),
                () -> assertEquals(customer.getRole(), userDetails.getClass())
        );
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
                .filter(u -> u.getUsername().equals("customer"))
                .findFirst()
                .get();
        final String customerToken = assertDoesNotThrow(() -> tokenService.generateToken(customer));

        // Parse the token and ensure that the customer is not an admin.
        Optional<UserPrincipal> var = assertDoesNotThrow(() -> tokenService.parseToken(customerToken));
        assertFalse(customer.isAdmin());

    }

}

