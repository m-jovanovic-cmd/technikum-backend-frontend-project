package technikumbackendfrontendproject.Backend.repository;

import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import technikumbackendfrontendproject.Backend.model.DTO.UserDTO;
import technikumbackendfrontendproject.Backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);

}


