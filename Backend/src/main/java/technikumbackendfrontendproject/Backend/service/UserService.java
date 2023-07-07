package technikumbackendfrontendproject.Backend.service;

import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepositiory;

    public UserService(UserRepository userRepositiory) {
        this.userRepositiory = userRepositiory;
    }

    public void registerUser(User user) {
        user.setRole("Customer");
        user.setStatus("active");
        userRepositiory.save(user);
    }
}