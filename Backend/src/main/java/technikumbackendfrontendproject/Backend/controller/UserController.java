package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import technikumbackendfrontendproject.Backend.model.DTO.UserDTO;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.security.UserPrincipal;
import technikumbackendfrontendproject.Backend.service.EntityNotFoundException;
import technikumbackendfrontendproject.Backend.service.TokenService;
import technikumbackendfrontendproject.Backend.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/createUserWithIsAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUserWithIsAdmin(@RequestBody UserDTO userDTO) {
        System.out.println("creating user (controller)");
        User createUser = userService.save(userDTO);
        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/get{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        var user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        try {
            User user = userService.getUser(id);
            // If the user exists, delete the user
            userService.deleteUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDto) {
        try {
            UserDTO updatedUser = userService.updateUser(id, updatedUserDto);
            // Convert the updated user to UserDto and return it in the response
            //UserDTO responseDto = userService.convertToUserDto(updatedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getUserId")
    public String getUserId(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        Optional<UserPrincipal> user = tokenService.parseToken(token);

        if (user.isPresent()) {
            return String.valueOf(user.get().getUserID());
        } else {
            return "Token does not contain a valid 'id'";
        }
    }
}