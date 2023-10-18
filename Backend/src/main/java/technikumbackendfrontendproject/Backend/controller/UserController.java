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

    /**
     * Create a new user in the system with administrative privileges.
     * This method is restricted to users with 'ADMIN' role.
     *
     * @param userDTO The UserDTO containing the user information for creation.
     * @return ResponseEntity containing the created User with administrative privileges, and an HTTP status code (OK).
     */
    @PostMapping("/createUserWithIsAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createUserWithIsAdmin(@RequestBody UserDTO userDTO) {
        System.out.println("Creating user (controller)");

        // Create a new user with administrative privileges
        User createUser = userService.save(userDTO);

        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }

    /**
     * Create a new user in the system.
     *
     * @param user The User entity representing the new user to be created.
     * @return ResponseEntity with an HTTP status code (OK) indicating a successful creation.
     */

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Retrieve a list of all users in the system.
     *
     * @return A list of User entities representing all users in the system.
     */
    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAll();
    }


    /**
     * Find a user by their unique identifier.
     *
     * @param id The unique identifier of the user to be retrieved.
     * @return ResponseEntity containing the found User if it exists, or a 'Not Found' response if the user is not found.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        var user = userService.findById(id);
        if (user.isEmpty()) {
            // Return a 'Not Found' response if the user is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Return a response with the found User
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }


    /**
     * Delete a user by their unique identifier.
     *
     * This method is restricted to users with 'ADMIN' role.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return ResponseEntity containing the deleted User if found and successfully deleted, or a 'Not Found' response if the user is not found.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        try {
            // Attempt to retrieve the user by their identifier
            User user = userService.getUser(id);

            // If the user exists, delete the user
            userService.deleteUser(id);

            // Return a response with the deleted User
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            // Return a 'Not Found' response if the user is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Update a user's information by their unique identifier.
     *
     * This method is restricted to users with 'ADMIN' role.
     *
     * @param id The unique identifier of the user to be updated.
     * @param updatedUserDto The UserDTO containing the updated user information.
     * @return ResponseEntity containing the updated UserDTO in the response body if successful, or a 'Not Found' response if the user is not found.
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDto) {
        try {
            // Attempt to update the user's information
            UserDTO updatedUser = userService.updateUser(id, updatedUserDto);
            // Log that the user has been updated            // Return a response with the updated UserDTO
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            // Return a 'Not Found' response if the user is not found
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