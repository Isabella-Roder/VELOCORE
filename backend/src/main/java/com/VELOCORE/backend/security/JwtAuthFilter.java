package com.VELOCORE.backend.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class JwtAuthFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    private final UsuarioDetailsService usuarioDetailsService;

    public JwtAuthFilter(
        JwtService jwtService,
        UsuarioDetailsService usuarioDetailsService
    ) {
        this.jwtService = jwtService;
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Override 
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            jwtService.extrairEmail(token).ifPresent(email -> {
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    try {
                        UserDetails usuario = usuarioDetailsService.loadUserByUsername(email);

                        if (usuario.isEnabled()) {
                            var auth = new UsernamePasswordAuthenticationToken(
                                usuario, null, usuario.getAuthorities()
                            );

                            SecurityContextHolder.getContext().setAuthentication(auth);
                        }
                    } catch (UsernameNotFoundException ignorado) {

                    }
                }
            });
        }

        chain.doFilter(request, response);
    }
}
