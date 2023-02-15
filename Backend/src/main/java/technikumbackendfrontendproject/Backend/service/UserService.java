package technikumbackendfrontendproject.Backend.service;

import org.springframework.stereotype.Service;

import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepositiory;

@Service
public class UserService {

    private UserRepositiory userRepositiory;

    public UserService(UserRepositiory userRepositiory) {
        this.userRepositiory = userRepositiory;
    }

    public void registerUser(User user) {
        user.setRole("Customer");
        user.setStatus("active");
        userRepositiory.save(user);
    }
}