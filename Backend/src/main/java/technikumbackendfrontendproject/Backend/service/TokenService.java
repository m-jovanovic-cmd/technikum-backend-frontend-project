package technikumbackendfrontendproject.Backend.service;

import java.security.Key;
import java.sql.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import technikumbackendfrontendproject.Backend.model.User;
import technikumbackendfrontendproject.Backend.security.UserPrincipal;


@Service
public class TokenService {
    
    private static final int EXPIRES_IN = 3600000;
    private static final SecretKey JWT_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    ////////////////////////////////////////////////////////
    // Methoden
    ////////////////////////////////////////////////////////

    public String generateToken(User user) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRES_IN);
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getEncoded());

        return Jwts.builder()
            .claim("id", user.getId())
            .claim("sub", user.getUsername())
            .claim("admin", user.isAdmin())
            .setExpiration(expirationDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public Optional <UserPrincipal> parseToken(String token) {
        Jws<Claims> jwsClaims;

        try {
            jwsClaims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET.getEncoded())
                .build()
                .parseClaimsJws(token);
        } catch (SignatureException e) {
            return Optional.empty();
        }

        Long userId = jwsClaims.getBody().get("id", Long.class);
        String username = jwsClaims.getBody().getSubject();
        boolean admin = jwsClaims.getBody().get("admin", Boolean.class);

        return Optional.of(new UserPrincipal(userId, username, admin));
    }
}