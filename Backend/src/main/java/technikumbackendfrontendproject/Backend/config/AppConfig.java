package technikumbackendfrontendproject.Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import technikumbackendfrontendproject.Backend.service.ProductFileUploadService;
import technikumbackendfrontendproject.Backend.service.RegistrationService;
import technikumbackendfrontendproject.Backend.service.UserService;

@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public RegistrationService registrationService() {
        return new RegistrationService();
    }

    @Bean
    public ProductFileUploadService productFileUploadService() {
        return new ProductFileUploadService();
    }

}
