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