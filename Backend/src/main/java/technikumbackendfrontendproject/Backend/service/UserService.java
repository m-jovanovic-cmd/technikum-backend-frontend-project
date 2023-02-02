package technikumbackendfrontendproject.Backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepositiory;

public class UserService {

    @Autowired
    private UserRepositiory userRepositiory;

    public void registerUser(User user) {
        user.setRole("Customer");
        user.setStatus("active");
        userRepositiory.save(user);
    }
}
