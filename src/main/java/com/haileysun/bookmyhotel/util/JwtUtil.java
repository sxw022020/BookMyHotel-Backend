package com.haileysun.bookmyhotel.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtil {
    /**
     * In total: 10 days
     *  - 1 second = 1000 milliseconds
     *  - 60: seconds in one minute
     *  - 60: minutes in one hour
     *  - 24: hours in one day
     *  - 10: 10 days
     */
    private static final long TOKEN_VALID_DURATION =  1000L * 60 * 60 * 24 * 10;

    @Value("{jwt.secret}")
    private String secret;

    /**
     * This code snippet generates a JSON Web Token (JWT) using the JJWT (Java JWT ) library. Here is what each line of the code does:
     *
     * Jwts.builder(): This creates a new JWT builder instance.
     *
     * .setClaims(new HashMap<>()): This sets the claims for the JWT. In this case, an empty map is used for the claims.
     *
     * .setSubject(subject): This sets the subject of the JWT, which is typically the user ID or some other unique identifier.
     *
     * .setIssuedAt(new Date(System.currentTimeMillis())): This sets the issued at time (iat) claim to the current time.
     *
     * .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 60 * 24)): This sets the expiration time (exp) claim to 24 hours from the current time.
     *
     * .signWith(SignatureAlgorithm.ES256, secret): This signs the JWT with the specified algorithm (in this case, ES256) and secret key.
     *
     * .compact(): This generates the final JWT as a compact string.
     *
     * Note that the choice of algorithm and secret key used for signing the JWT should be carefully considered for security purposes.
     * Also, the expiration time set in this code snippet (24 hours) may not be appropriate for all use cases and should be adjusted as needed.
     */
    public String generateToken(String subject) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALID_DURATION)) // cast to long
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Get claim
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    // Get details in the claim
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    public Boolean validateToken(String token) {
        return extractExpiration(token).after(new Date());
    }
}
