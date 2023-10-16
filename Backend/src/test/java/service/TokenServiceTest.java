package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import technikumbackendfrontendproject.Backend.BackendApplication;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;
import technikumbackendfrontendproject.Backend.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class TokenServiceTest {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        User customer = new User();
        customer.setId(1L);
        customer.setUsername("customer");
        customer.setPassword("customerPassword");
        customer.setEmail("customer@email.com");
        customer.setRole("Customer");
        customer.setGender("female");

        customer.setFirstName("customerFirst");
        customer.setLastName("customerLast");
        User admin = new User();
        admin.setId(2L);
        admin.setUsername("admin");
        admin.setPassword("adminPassword");
        admin.setEmail("admin@email.com");
        admin.setRole("Admin");

        admin.setGender("male");
        admin.setFirstName("adminFirst");
        admin.setLastName("adminLast");

        User user3 = new User("weiblich", "inactiveuser", "securepass", "Alice", "Johnson", "alice@email.com", "5678", "Los Angeles", "Park Avenue", "10", "Inaktiv", "User");
        User user4 = new User("männlich", "admin", "adminpass123", "Admin", "Smith", "admin@admin.com", "4321", "Chicago", "Admin Street", "1", "Aktiv", "Administrator");
        userRepository.saveAll(List.of(customer, admin, user3, user4));
    }
    @Test
    void generateTokenTest() {
        final User customer = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals("customer"))
                .findFirst()
                .get();
        final String customerToken = assertDoesNotThrow(() -> tokenService.generateToken(customer));
        User wrongCustomer = customer;
        wrongCustomer.setUsername(null);
        assertThrows(IllegalArgumentException.class,
                () -> tokenService.generateToken(wrongCustomer));
        // tests for token?
    }
    //generate Token

    //parseToken


}
