package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;
import technikumbackendfrontendproject.Backend.service.EntityNotFoundException;
import technikumbackendfrontendproject.Backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("creating user (controller)");
        User createUser = userService.saveUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }


    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/get{id}")
    public User findUserById(@PathVariable Long id) {
        var user = userService.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user name not found");
        }
        return user.get();
    }


    @DeleteMapping("/delete/{id}")
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
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    // Update the user's information with the data from newUser
                    user.setUsername(newUser.getUsername());
                    // Update other fields as needed
                    user.setStatus((newUser.getStatus()));
                    user.setRole(newUser.getRole());
                    user.setEmail(newUser.getEmail());
                    user.setGender(newUser.getGender());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastname());
                    user.setLocation(newUser.getLocation());
                    user.setPassword(newUser.getPassword());
                    user.setPostcode(newUser.getPostcode());
                    user.setStreet(newUser.getStreet());
                    user.setStreetnumber(newUser.getStreetnumber());
                    // Save the updated user
                    User updatedUser = userService.saveUser(user);

                    // Return the updated user as a response
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }



}
/*

 */


/*
 * Zusammengefasst > "Macht viele coole Sachen" - Zitat Mladen
 * Haben uns das JASON vom Javascript schicken lassen, dies ist die 1. Instanz
 * 
 * @RestController > schreibt man immer wenn man Springboot verwendet
 * 
 * @RequestMapping auf welche URL(Pfad) zugegriffen wird
 * Autowired: wenn es gebraucht wird, wird eine Instanz von registrationService
 * erstellt
 * 
 * @PostMapping
 * Alle Subpfade weitermappen (Localhost 8080/registration/register > dann wird
 * die Methode public ResponeEntity ausgeführt)
 * 
 * Was passiert in methode ResponseEntity (RE): RE übernimmt Objekt Long und es
 * schickt den HTTP-Status zurück
 * 
 * @RequestBody
 * der Postrequest den wir von Javascript bekommen haben, Data von Typ JSON ein
 * Objekt, haben wir gemoddelt auf die Klasse Registration > weiter zu
 * registration.java
 * 
 * Zurück von registration.java
 * bekommen @Requestbody übergeben mit variablenname registration (wie zb String
 * Name)
 * 
 * rufen createRegistration auf und übergeben variable registration(der klasse
 * registration)
 * 
 * Wir gehen jetzt in registrationservice rein
 */
