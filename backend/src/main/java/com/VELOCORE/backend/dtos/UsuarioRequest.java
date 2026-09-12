package com.VELOCORE.backend.dtos;

import com.VELOCORE.backend.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome deve conter no máximo 150 caracteres")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 255, message = "E-mail deve conter no máximo 255 caracteres")
        String email,

        @NotBlank(message = "Senha é obrigatório")
        @Size(
                min = 8,
                max = 255,
                message = "Senha deve conter entre 8 a 255 caracteres"
        )
        String senha,

        @NotNull(message = "Tipo do usuário é obrigatório")
        TipoUsuario tipo
) {
}
