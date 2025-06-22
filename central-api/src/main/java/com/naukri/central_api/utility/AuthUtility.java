package com.naukri.central_api.utility;

import com.naukri.central_api.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthUtility {

    @Value("${secret.password}")
    String secretPassword;

    Long expirationTime = 10000000L;

    UserService userService;

    public AuthUtility(UserService userService) {
        this.userService = userService;
    }

    public String generateToken(String email,
                                String password,
                                String role) {

        String payload = email + ":" + password + ":" + role;

        String jwtToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretPassword)
                .setSubject(payload)
                .compact();

        return jwtToken;

    }

    public String decryptToken(String token) {

        String payload = Jwts.parser().setSigningKey(secretPassword)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return payload;

    }

    public boolean validateToken(String token) {

        String payload = this.decryptToken(token);
        String [] details = payload.split(":");
        String email = details[0];
        String password = details[1];

        return userService.validateCredentials(email, password);

    }

}
