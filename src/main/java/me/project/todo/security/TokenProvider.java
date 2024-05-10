package me.project.todo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class TokenProvider {

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
    }

    public Authentication getAuthentication(String token) {
        throw new UnsupportedOperationException("Unimplemented method 'getAuthentication'");
    }


}