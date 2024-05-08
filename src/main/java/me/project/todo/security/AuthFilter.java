package me.project.todo.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("null")
@Component
public class AuthFilter extends OncePerRequestFilter {

    private final TokenProvider jwtTokenProvider;

    @Autowired
    public AuthFilter(TokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthFilter() {
        this.jwtTokenProvider = new TokenProvider();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken(request);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            // Handle exceptions if needed
        }

        filterChain.doFilter(request, response);
    }
}

