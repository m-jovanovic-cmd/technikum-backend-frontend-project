package technikumbackendfrontendproject.Backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import technikumbackendfrontendproject.Backend.model.User;

@Repository
public interface UserRepositiory extends JpaRepository<User, Long> {
    
}
