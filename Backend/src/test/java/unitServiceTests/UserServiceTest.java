package unitServiceTests;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class UserServiceTest {
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
        User user1 = new User("weiblich", false,"username", "passwort", "firstname", "lastname", "mail@mail.at", "1111", "Lustenau", "Straße", "2", "Aktiv", "User" );
        User user2 = new User("männlich", false, "user123", "mypassword123", "John", "Doe", "johndoe@email.com", "1234", "New York", "Main Street", "5", "Aktiv", "User");
        User user3 = new User("weiblich", false, "inactiveuser", "securepass", "Alice", "Johnson", "alice@email.com", "5678", "Los Angeles", "Park Avenue", "10", "Inaktiv", "User");
        User user4 = new User("männlich", false, "admin", "adminpass123", "Admin", "Smith", "admin@admin.com", "4321", "Chicago", "Admin Street", "1", "Aktiv", "Administrator");
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

    @Test
    void findByIdTest(){
        // Retrieve a list of all users from the UserRepository, ensuring it doesn't throw any exceptions.
        List<User> allUsers = assertDoesNotThrow(() -> userRepository.findAll());
        // Extract ID of first user from list
        final Long userId = allUsers.stream().findFirst().get().getId();
        // Retrieve User from UserService using extracted user ID
        // Ensuring no exceptions are thrown.
        User user = assertDoesNotThrow(() -> userService.findById(userId));
    }
    @Test
    void findByWrongIdTest() {
        // Define invalid user ID
        final Long wrongId = 999999L;
        // Ensures: 'findById'+invalid user ID throws -> RuntimeException.
        assertThrows(RuntimeException.class,
                () -> userService.findById(wrongId));
    }

    @Test
    void registerValidUserTest() {
        // Create a new valid user for registration.
        User user5 = new User("männlich", false, "admin", "adminpass123", "Admin", "Smith", "admin@admin.com", "4321", "Chicago", "Admin Street", "1", "Aktiv", "Administrator");
        // Register the new user using the 'registerUser' method.
        userService.registerUser(user5);
        // Retrieve a list of all users from UserRepository to check the total number of users.
        List<User> allUsers = assertDoesNotThrow(() -> userRepository.findAll());
        // Verify that the total number of users has increased by one after user registration.
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
        assertThrows(Exception.class, () -> userService.registerUser(user6));

        // Ensure that the number of users in the repository remains the same
        List<User> allUsersAfter = assertDoesNotThrow(() -> userRepository.findAll());
        int userCountAfter = allUsersAfter.size();
        assertEquals(initialUserCount, userCountAfter);
    }


}
