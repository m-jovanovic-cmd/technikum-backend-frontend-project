package technikumbackendfrontendproject.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.*;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements saveUser {

    private UserRepository userRepositiory;
    private String convertedID;

    @Autowired
    public UserService(UserRepository userRepositiory) {
        this.userRepositiory = userRepositiory;
    }
    @Override
    public User saveUser(User user) {
        return userRepositiory.save(user);
    }
    public void registerUser(User user) {
        user.setRole("Customer");
        user.setStatus("active");
        userRepositiory.save(user);
    }

    public List<User> findAll() {
        return userRepositiory.findAll();
    }

    public User getUser(Long id) {
        var user = userRepositiory.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user2 = user.get();
        return user2;
    }

    public Optional<User> findById(Long id) {
        return userRepositiory.findById(id);

    }

   /*
   *  @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepositiory.save(newUser);
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        convertedID = String.valueOf(id);
        return userRepositiory.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    return userRepositiory.save(user);

                }).orElseThrow(() -> new UsernameNotFoundException(convertedID));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        convertedID = String.valueOf(id);
        if(!userRepositiory.existsById((id))){
            throw new UsernameNotFoundException(convertedID);
        }
        userRepositiory.deleteById(id);
        return  "User with id "+id+" has been deleted successfully.";
    }
   * */

}




