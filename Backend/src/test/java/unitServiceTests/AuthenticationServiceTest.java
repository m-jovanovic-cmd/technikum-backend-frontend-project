package unitServiceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import technikumbackendfrontendproject.Backend.BackendApplication;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;
import technikumbackendfrontendproject.Backend.service.AuthenticationService;
import technikumbackendfrontendproject.Backend.service.TokenService;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class AuthenticationServiceTest {
    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticationService = new AuthenticationService(tokenService, userRepository);
    }

    @Test
    void loginWithValidCredentials() {
        // Mock UserRepository to return a user when findByUsernameAndPassword is called
        User validUser = new User();
        validUser.setId(1L);
        validUser.setUsername("testuser");
        when(userRepository.findByUsernameAndPassword(any(), any())).thenReturn(Optional.of(validUser));

        // Mock TokenService to return a token when generateToken is called
        when(tokenService.generateToken(validUser)).thenReturn("generated-token");

        String token = authenticationService.login("testuser", "password");

        assertNotNull(token);
        assertEquals("generated-token", token);
    }

    @Test
    void loginWithInvalidCredentials() {
        // Mock UserRepository to return an empty result when findByUsernameAndPassword is called
        when(userRepository.findByUsernameAndPassword(any(), any())).thenReturn(Optional.empty());

        // Ensure that a RuntimeException is thrown when invalid credentials are used
        assertThrows(RuntimeException.class, () -> authenticationService.login("invaliduser", "invalidpassword"));
    }
}
