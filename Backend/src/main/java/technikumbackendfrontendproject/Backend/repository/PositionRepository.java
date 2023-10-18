package technikumbackendfrontendproject.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import technikumbackendfrontendproject.Backend.model.Position;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findAllByCart_Id(Long cartId);

}
