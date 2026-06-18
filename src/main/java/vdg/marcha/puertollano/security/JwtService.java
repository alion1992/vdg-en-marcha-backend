package vdg.marcha.puertollano.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "vdg-en-marcha-clave-super-secreta-para-jwt-2026";

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes(
                            StandardCharsets.UTF_8));

    public String generateToken(String dni) {

        return Jwts.builder()

                .subject(dni)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000L * 60 * 60 * 24
                        )
                )

                .signWith(key)

                .compact();
    }

    public String extractUsername(String token) {

        return extractClaims(token)
                .getSubject();
    }

    public boolean validateToken(String token) {

        try {

            Claims claims =
                    extractClaims(token);

            return claims
                    .getExpiration()
                    .after(new Date());

        } catch (Exception e) {

            return false;
        }
    }

    private Claims extractClaims(String token) {

        return Jwts.parser()

                .verifyWith(key)

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }

}