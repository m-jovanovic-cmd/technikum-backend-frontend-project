package technikumbackendfrontendproject.Backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.repository.UserRepositiory;

public class UserService {

    @Autowired
    private UserRepositiory testRepo;

    public void createUser(String email) {
        User testUser = new User(email);
        testUser.getEmail();
        testRepo.save(testUser);
    }

    private boolean checkEmail(User user){
        return true;
    }
}
