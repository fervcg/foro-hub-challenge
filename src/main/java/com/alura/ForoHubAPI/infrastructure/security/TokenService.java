package com.alura.ForoHubAPI.infrastructure.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alura.ForoHubAPI.domain.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.validation.ValidationException;


@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String API_SECRET;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(API_SECRET);
            return JWT.create()
                    .withIssuer("API FORO HUB")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getUserId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // invalid signing configuration // Couldn't convert claim
            throw new RuntimeException();
        }
    }

    public String getSubject(String token){
        if (token == null) {
            throw new ValidationException("EL TOKEN NO PUEDE SER NULO");
        }

        DecodedJWT verifier = null;

        try{
            Algorithm algorithm = Algorithm.HMAC256(API_SECRET);
            verifier = JWT.require(algorithm)
                            .withIssuer("API FORO HUB")
                            .build()
                            .verify(token);
                verifier.getSubject();
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }

        if (verifier.getSubject()==null) {
            throw new RuntimeException("No pudo ser encontrado el subject del token");
        }
        return verifier.getSubject();

    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
