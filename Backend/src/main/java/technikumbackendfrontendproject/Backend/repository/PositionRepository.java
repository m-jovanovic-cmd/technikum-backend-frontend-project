package technikumbackendfrontendproject.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import technikumbackendfrontendproject.Backend.model.Position;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(value = "SELECT p FROM cart c " +
            "JOIN c.user u " +
            "JOIN c.positions p " +
            "WHERE u.id = :id")
    Optional<Position> findByUserId(@Param("id") Long userId);



    
}
