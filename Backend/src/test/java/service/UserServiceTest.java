package service;

import jakarta.xml.bind.ValidationException;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import technikumbackendfrontendproject.Backend.BackendApplication;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;
import technikumbackendfrontendproject.Backend.service.UserService;
import technikumbackendfrontendproject.Backend.service.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setup(){
        User user1 = new User("weiblich", "username", "passwort", "firstname", "lastname", "mail@mail.at", "1111", "Lustenau", "Straße", "2", "Aktiv", "User" );
        User user2 = new User("männlich", "user123", "mypassword123", "John", "Doe", "johndoe@email.com", "1234", "New York", "Main Street", "5", "Aktiv", "User");
        User user3 = new User("weiblich", "inactiveuser", "securepass", "Alice", "Johnson", "alice@email.com", "5678", "Los Angeles", "Park Avenue", "10", "Inaktiv", "User");
        User user4 = new User("männlich", "admin", "adminpass123", "Admin", "Smith", "admin@admin.com", "4321", "Chicago", "Admin Street", "1", "Aktiv", "Administrator");

        userRepository.saveAll(List.of(user1, user2, user3, user4));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();

    }

    @Test
    void findByIdTest(){
        List<User> allUsers = assertDoesNotThrow(() -> userRepository.findAll());
        final Long userId = allUsers.stream().findFirst().get().getId();
        User user = assertDoesNotThrow(() -> userService.findById(userId));
    }
    @Test
    void findByWrongIdTest() {
        final Long wrongId = 999999L;

        assertThrows(RuntimeException.class,
                () -> userService.findById(wrongId));
    }

    @Test
    void registerValidUserTest() {
        User user5 = new User("männlich", "admin", "adminpass123", "Admin", "Smith", "admin@admin.com", "4321", "Chicago", "Admin Street", "1", "Aktiv", "Administrator");
        userService.registerUser(user5);
        List<User> allUsers = assertDoesNotThrow(() -> userRepository.findAll());
        assertEquals(5, allUsers.size());

    }

    @Test
    void registerInvalidUserTest() {
        // Create an incomplete or invalid user
        User user6 = new User(); // Set this user as needed

        // Get the initial number of users in the repository
        List<User> allUsersBefore = assertDoesNotThrow(() -> userRepository.findAll());
        int initialUserCount = allUsersBefore.size();

        // Attempt to register the invalid user and expect an exception
        assertThrows(DataIntegrityViolationException.class, () -> userService.registerUser(user6));

        // Ensure that the number of users in the repository remains the same
        List<User> allUsersAfter = assertDoesNotThrow(() -> userRepository.findAll());
        int userCountAfter = allUsersAfter.size();
        assertEquals(initialUserCount, userCountAfter);
    }


}
