package technikumbackendfrontendproject.Backend.repository;

import java.util.List;
import technikumbackendfrontendproject.Backend.model.product;

public interface ProductRepository {
    
    List<product> findAll();

    List<product> findAllByType(String type);

}
