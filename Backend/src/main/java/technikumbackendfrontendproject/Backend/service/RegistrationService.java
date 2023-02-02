package technikumbackendfrontendproject.Backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import technikumbackendfrontendproject.Backend.model.Registration;
import technikumbackendfrontendproject.Backend.repository.RegistrationRepository;


public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepo;

    public void createRegistration(Registration registration) {
        registrationRepo.save(registration);
    }

    public List<Registration> findAll() {
        return registrationRepo.findAll();
    }
}


/*
 * Siehe erklären Autowired
 * 
 * wir instanzieren RegisterRepository(RR)
 * darinst steht die methode createRegistration
 * Dajj raufen wir registerRepo auf(Instanzname)
 * und verwenden save function (ist in library drin)
 * und übergen ihr registration
 * 
 * Wir sagen der Datenbank speichere unsere Daten!
 * 
 * Gehe zu RegisterRepostiry
 */