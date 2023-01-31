package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technikumbackendfrontendproject.Backend.model.Registration;
import technikumbackendfrontendproject.Backend.service.RegistrationService;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
    
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<Long> createRegistration(@RequestBody Registration registration) {
        registrationService.createRegistration(registration); 
        return new ResponseEntity<>(HttpStatus.OK);
    }
}