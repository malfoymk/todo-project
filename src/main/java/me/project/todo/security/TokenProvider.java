package me.project.todo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class TokenProvider {

    public String resolveToken(HttpServletRequest request) { // Get token from request
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) { // Validate token
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
        //  boolean isValid = request.getAuthentication().isAuthenticated();
        //  if (!isValid) {
        //      throw new RuntimeException("Invalid token");
        //  }
        //  return isValid;
     }

    public Authentication getAuthentication(String token) { // Get authentication from token
        throw new UnsupportedOperationException("Unimplemented method 'getAuthentication'");
    }


}
