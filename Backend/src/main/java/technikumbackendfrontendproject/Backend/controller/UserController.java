package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technikumbackendfrontendproject.Backend.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService testService;

    
    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody String email) {
        testService.createUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}