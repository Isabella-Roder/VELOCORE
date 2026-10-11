package com.VELOCORE.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VELOCORE.backend.dtos.LoginRequest;
import com.VELOCORE.backend.dtos.LoginResponse;
import com.VELOCORE.backend.dtos.UsuarioResponse;
import com.VELOCORE.backend.security.JwtService;
import com.VELOCORE.backend.security.UsuarioDetails;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest request
    ) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        UsuarioDetails detalhes = (UsuarioDetails) auth.getPrincipal();
        String token = jwtService.gerarToken(detalhes);

        return ResponseEntity.ok(new LoginResponse(token, UsuarioResponse.from(detalhes.getUsuario())));
    }
}
