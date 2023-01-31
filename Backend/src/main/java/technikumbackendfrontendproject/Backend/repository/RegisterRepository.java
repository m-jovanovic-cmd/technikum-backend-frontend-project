package technikumbackendfrontendproject.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technikumbackendfrontendproject.Backend.model.Registration;

@Repository
public interface RegisterRepository extends JpaRepository<Registration, Long>{
    
}
