package technikumbackendfrontendproject.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technikumbackendfrontendproject.Backend.model.Product;

@Repository
public interface ProductFileUploadRepository extends JpaRepository<Product, Long> {
    
}
