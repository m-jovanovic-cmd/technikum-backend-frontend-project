package technikumbackendfrontendproject.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import technikumbackendfrontendproject.Backend.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    
}
