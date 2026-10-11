package com.VELOCORE.backend.dtos;

public record LoginResponse(
    String token,
    UsuarioResponse usuario
) {
    
}
