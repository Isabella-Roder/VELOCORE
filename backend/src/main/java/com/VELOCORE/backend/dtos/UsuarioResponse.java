package com.VELOCORE.backend.dtos;

import com.VELOCORE.backend.enums.TipoUsuario;
import com.VELOCORE.backend.models.Usuario;

import java.time.Instant;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        TipoUsuario tipo,
        boolean ativo,
        Instant criado
) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipo(),
                usuario.isAtivo(),
                usuario.getCriado()
        );
    }
}
