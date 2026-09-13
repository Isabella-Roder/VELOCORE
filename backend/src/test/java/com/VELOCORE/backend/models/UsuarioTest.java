package com.VELOCORE.backend.models;

import com.VELOCORE.backend.exception.RegraDeNegocioException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {

    @Test
    void deveDesativarUsuarioAtivo() {
        Usuario usuario = new Usuario();
        usuario.setAtivo(true);

        usuario.desativar();

        assertEquals(false, usuario.isAtivo());
    }

    @Test
    void deveRecusarDesativarUsuarioAtivo() {
        Usuario usuario = new Usuario();
        usuario.setAtivo(false);

        assertThrows(RegraDeNegocioException.class, () -> usuario.desativar());
    }

    @Test
    void deveAtivarUsuarioDesativado() {
        Usuario usuario = new Usuario();
        usuario.setAtivo(false);

        usuario.ativar();

        assertEquals(true, usuario.isAtivo());
    }

    @Test
    void deveRecusarAtivarUsuarioAtivo() {
        Usuario usuario = new Usuario();
        usuario.setAtivo(true);

        assertThrows(RegraDeNegocioException.class, () -> usuario.ativar());
    }

}
