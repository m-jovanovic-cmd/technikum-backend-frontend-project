package technikumbackendfrontendproject.Backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import technikumbackendfrontendproject.Backend.model.DTO.UserDTO;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;
import technikumbackendfrontendproject.Backend.service.EntityNotFoundException;
import technikumbackendfrontendproject.Backend.service.UserService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private UserService userService;


    //remove saveUserInterface and add call repository
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    //public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        System.out.println("creating user (controller)");
        User createUser = userService.save(userDTO);
        return new ResponseEntity<>(createUser, HttpStatus.OK);
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
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody  @Valid UserDTO updatedUserDto) {
        try {
            UserDTO updatedUser = userService.updateUser(id, updatedUserDto);

            // Convert the updated user to UserDto and return it in the response
            //UserDTO responseDto = userService.convertToUserDto(updatedUser);

            logger.info("User with id: " + id + " updated!");

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


