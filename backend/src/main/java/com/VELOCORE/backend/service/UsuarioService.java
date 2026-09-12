package com.VELOCORE.backend.service;

import com.VELOCORE.backend.dtos.UsuarioRequest;
import com.VELOCORE.backend.dtos.UsuarioResponse;
import com.VELOCORE.backend.exception.RecursoNaoEncontradoException;
import com.VELOCORE.backend.exception.RegraDeNegocioException;
import com.VELOCORE.backend.models.Usuario;
import com.VELOCORE.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private Usuario buscarEntidade(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com ID: " + id));
    }

    @Transactional
    public UsuarioResponse cadastrar(UsuarioRequest request) {

        if (usuarioRepository.existsByEmail(request.email())) {
            throw new RegraDeNegocioException("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario(
                request.nome(),
                request.email(),
                passwordEncoder.encode(request.senha()),
                request.tipo()
        );

        return UsuarioResponse.from(usuarioRepository.save(usuario));
    }

    @Transactional
    public void desativar(UUID id) {
        Usuario usuario = buscarEntidade(id);
        usuario.desativar();
    }

    @Transactional
    public void ativar(UUID id) {
        Usuario usuario = buscarEntidade(id);
        usuario.ativar();
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
                .stream().map(UsuarioResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(UUID id) {
        return UsuarioResponse.from(buscarEntidade(id));
    }

}
