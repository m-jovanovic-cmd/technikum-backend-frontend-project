package technikumbackendfrontendproject.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technikumbackendfrontendproject.Backend.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}

/*
 * Repos sind interfaces!
 * 
 * Extende zu JPA repo
 * 
 * Links sage ich Repo was ihr übergeben wird (die klasse Registration),
 * Long für die ID die er generated (siehe in model- Registration)
 * 
 * > Daten gehen in die Datenbank
 */
