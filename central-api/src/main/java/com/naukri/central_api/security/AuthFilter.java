package com.naukri.central_api.security;

import com.naukri.central_api.utility.AuthUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {

    AuthUtility authUtility;

    @Autowired
    public AuthFilter (AuthUtility authUtility) {
        this.authUtility = authUtility;
    }

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            String token = bearerToken.substring(7);
            // We got the token now we need to validate that this token is a genuine token or not.
            boolean isValid = authUtility.validateToken(token);
            if(isValid == false){
                // I am not going to set any kind of authentication, and I will return from here itself
                // before filtering if I am not setting any kind of authentication that
                // means I am rejecting the request
                filterChain.doFilter(request, response);
                return;
            }
            String payload = authUtility.decryptJwtToken(token);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(payload, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response); // If you are not setting up username and password authentication, that means you are rejecting token

    }

}
