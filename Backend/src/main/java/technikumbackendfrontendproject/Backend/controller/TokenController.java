package technikumbackendfrontendproject.Backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TokenController {
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @GetMapping("/validateToken")
    public Object currentUserID(@RequestHeader Authentication authentication) {
        // Implement token validation logic here
        // Return user information if the token is valid
        logger.info("DETAILS: " + authentication.getDetails());
        return authentication.getDetails();
    }
}
