package technikumbackendfrontendproject.Backend.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        user.setRole("Customer");
        user.setStatus("active");
        userRepository.save(user);
    }

    public User findById(Long userId) {
        try {
            return userRepository.findById(userId).get();
        } catch(ObjectNotFoundException e) {
            throw new RuntimeException("no User with id: " + userId);
        }
    }
}