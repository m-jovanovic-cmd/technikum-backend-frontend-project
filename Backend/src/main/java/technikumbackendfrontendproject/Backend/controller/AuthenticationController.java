package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return "Bearer " + authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }
}