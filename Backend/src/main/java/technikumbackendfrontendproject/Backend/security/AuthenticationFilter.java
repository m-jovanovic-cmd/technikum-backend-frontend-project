package technikumbackendfrontendproject.Backend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import technikumbackendfrontendproject.Backend.service.TokenService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    // /////////////////////////////////////////////////////////////////////////
    // Init
    // /////////////////////////////////////////////////////////////////////////

    public AuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    // /////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Get authorization header
        String bearer = request.getHeader("Authorization");

        // Request does not contain authorization header or authorization header is not bearer
        if (bearer == null || !bearer.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Create authorization token
        Optional<UsernamePasswordAuthenticationToken> authToken = createAuthToken(bearer.split(" ")[1]);

        // JWT is invalid, auth token could not be created
        if (authToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        // JWT is valid, set auth token
        SecurityContextHolder.getContext().setAuthentication(authToken.get());
        filterChain.doFilter(request, response);
    }

    private Optional<UsernamePasswordAuthenticationToken> createAuthToken(String jwt) {
        // Parse JWT
        Optional<UserPrincipal> userPrincipal = tokenService.parseToken(jwt);

        // JWT is invalid
        if (userPrincipal.isEmpty()) {
            return Optional.empty();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        // Add admin role if user is admin
        if (userPrincipal.get().isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        // Create auth token
        return Optional.of(new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities));
    }
}