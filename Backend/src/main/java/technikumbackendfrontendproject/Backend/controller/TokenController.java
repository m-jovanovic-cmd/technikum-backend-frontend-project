package technikumbackendfrontendproject.Backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TokenController {
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @PostMapping("/validateToken")
    public Object currentUserID(@RequestBody Authentication authentication) {
        // Implement token validation logic here
        // Return user information if the token is valid
        logger.info("DETAILS: " + authentication.getDetails());
        return authentication.getDetails();
    }
}
