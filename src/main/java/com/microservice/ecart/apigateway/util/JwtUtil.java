package com.microservice.ecart.apigateway.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // 256-bit secure secret key string matching cryptographic standards
    private static final String SECRET = "your32characterlongsecretkeyhere12345"; 
    private static final long EXPIRATION_TIME = 86400000; // 24 Hours

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    /**
     * Generates a stateless JWT token using the verified user email as the subject.
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email) // Tokenizes the unique email address parameter string
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)  // JJWT detects the required secure HS256 configuration parameters
                .compact();
    }

    /**
     * Extracts and returns the token subject string (the email) after successful verification.
     * Throws an exception automatically if the payload is malformed or expired.
     */
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject(); // Returns the email address string embedded in the token
    }

    /**
     * Verifies the cryptographic integrity and structure of an incoming token string.
     */
    public boolean validateToken(final String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true; // Token string parameters are authentic and unexpired
        } catch (Exception e) {
            return false; // Token signature verification failed or expiration met
        }
    }
}
