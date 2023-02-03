package technikumbackendfrontendproject.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import technikumbackendfrontendproject.Backend.model.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {
    
}
