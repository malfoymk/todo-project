package me.project.todo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;


    
@Component
public class TokenProvider {
        
    private static final String secretKey = "mySecretKey"; // Secret key
    
    public String resolveToken(HttpServletRequest request) { // Get token from request
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(HttpServletRequest request, String token) { // Validate token
        try {
            Jws<Claims> claims = Jwts.parser()
                         .setSigningKey(secretKey)
                         .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
     }

    public Authentication getAuthentication(String token) { // Get authentication from token
        throw new UnsupportedOperationException("Unimplemented method 'getAuthentication'");
    }


}
