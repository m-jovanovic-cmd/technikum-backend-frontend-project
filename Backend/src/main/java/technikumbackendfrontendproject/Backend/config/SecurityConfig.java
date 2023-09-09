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
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable security for testing purposes
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**") // Match all URLs
                                .permitAll() // Allow all requests
                )
                // Add other security configurations if needed
                .csrf().disable(); // Disable CSRF protection for testing (not recommended in production)

        return http.build();
    }
}

/*
*
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
                    // Allow unauthorized requests to certain endpoints
                    .authorizeHttpRequests().requestMatchers("/login", "/api/users/**", "/api/users", "/api/users/delete/{id}", "/api/products", "/public/**").permitAll()
                    // Authenticate all other requests
                    .anyRequest().authenticated()
                    .and()
                    // Add filter to validate tokens with every request
                    .addFilterBefore(new AuthenticationFilter(tokenService),
                                     UsernamePasswordAuthenticationFilter.class);



        return httpSecurity.build();
    }
*
* */