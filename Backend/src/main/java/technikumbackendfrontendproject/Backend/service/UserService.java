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

    private UserRepository userRepository;
    private String convertedID;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
       convertedID = String.valueOf(id);
        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            // User not found, you can handle this as needed (e.g., throw an exception or return a status)
            throw new EntityNotFoundException("User with ID " + convertedID + " not found");
        }
    }
    public void registerUser(User user) {
        user.setRole("Customer");
        user.setStatus("active");
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user2 = user.get();
        return user2;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);

    }

}




