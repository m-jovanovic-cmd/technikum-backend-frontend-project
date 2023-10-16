package service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import technikumbackendfrontendproject.Backend.BackendApplication;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class CartServiceTest {
}
