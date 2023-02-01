package technikumbackendfrontendproject.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import technikumbackendfrontendproject.Backend.model.Registration;
import technikumbackendfrontendproject.Backend.repository.RegisterRepository;

public class RegistrationService {

    @Autowired
    private RegisterRepository registerRepo;

    public void createRegistration(Registration registration) {
        registerRepo.save(registration);
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