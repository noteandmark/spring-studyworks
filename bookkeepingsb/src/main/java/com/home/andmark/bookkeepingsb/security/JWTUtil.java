package com.home.andmark.bookkeepingsb.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now()
                .plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("surname", username)
                .withIssuedAt(new Date())
                .withIssuer("bookkeeping")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("bookkeeping")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("surname").asString();
    }
}
