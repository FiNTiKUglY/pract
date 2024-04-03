package com.library.api.libraryapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(UUID id, String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("sub")
                .withClaim("id", id.toString())
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("iss")
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        var verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("sub")
                .withIssuer("iss")
                .build();

        var jwt = verifier.verify(token);

        return jwt.getClaim("email").asString();
    }
}