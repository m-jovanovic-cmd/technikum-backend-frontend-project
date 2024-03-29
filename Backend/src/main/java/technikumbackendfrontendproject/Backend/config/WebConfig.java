package technikumbackendfrontendproject.Backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure Cross-Origin Resource Sharing (CORS) for the application.
     * This method allows requests from all origins and all HTTP methods for all endpoints.
     *
     * @param registry The CorsRegistry used for CORS configuration.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")        // Allow CORS for all endpoints.
                .allowedMethods("*")       // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.).
                .allowedOrigins("*");      // Allow requests from all origins (domains).
    }
}