package technikumbackendfrontendproject.Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import technikumbackendfrontendproject.Backend.security.AuthenticationFilter;
import technikumbackendfrontendproject.Backend.service.TokenService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenService tokenService;
    // /////////////////////////////////////////////////////////////////////////
    // Init
    // /////////////////////////////////////////////////////////////////////////

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }
 
    // /////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Configure the security filter chain for the application.
     * This method sets up security-related configurations, including disabling CSRF protection,
     * enabling CORS, setting session management to stateless, and specifying authorization rules for different endpoints.
     *
     * @param httpSecurity The HttpSecurity object used to configure security settings.
     * @return A configured SecurityFilterChain for the application.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // Disable csrf
        httpSecurity.csrf().disable()
                    // Enable cors
                    .cors()
                    .and()
                    // Set session management to stateless
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeHttpRequests().requestMatchers("/login", "/api/users", "/api/users/update/{id}","/api/users/get/{id}","/api/products/{type}","/api/products","/api/products/details/{id}", "/api/users/delete/{id}", "/public/**", "/api/taxes").permitAll()
                    // Authenticate all other requests
                    .anyRequest().authenticated()
                    .and()
                    // Add filter to validate tokens with every request
                    .addFilterBefore(new AuthenticationFilter(tokenService),
                                     UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }
}
