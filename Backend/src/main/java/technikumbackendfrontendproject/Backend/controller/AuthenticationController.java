package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import technikumbackendfrontendproject.Backend.model.DTO.LoginDTO;
import technikumbackendfrontendproject.Backend.service.AuthenticationService;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Authenticate a user with the provided login credentials and return an authentication token.
     *
     * @param loginDTO The LoginDTO containing the username and password for authentication.
     * @return A string containing a bearer token if authentication is successful.
     */
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return "Bearer " + authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }

    /**
     * Check if the current user has 'ADMIN' role privileges.
     *
     * @return A Boolean indicating whether the current user has 'ADMIN' role privileges.
     */
    @GetMapping("/isadmin")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean isAdmin() {
        return true;
    }

}