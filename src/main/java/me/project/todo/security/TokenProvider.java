package me.project.todo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class TokenProvider {

    public String resolveToken(HttpServletRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'resolveToken'");
    }

    public boolean validateToken(String token) {
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
    }

    public Authentication getAuthentication(String token) {
        throw new UnsupportedOperationException("Unimplemented method 'getAuthentication'");
    }


}