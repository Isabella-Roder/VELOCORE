package com.VELOCORE.backend.models;

import com.VELOCORE.backend.enums.TipoUsuario;
import com.VELOCORE.backend.exception.RegraDeNegocioException;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = 255)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoUsuario tipo;

    @Column(nullable = false, name = "ativo")
    private boolean ativo = true;

    @Column(name = "criado", nullable = false, updatable = false)
    private Instant criado;

    @Column(name = "atualizado", nullable = false)
    private Instant atualizado;

    public Usuario() {

    }

    public Usuario(
            String nome,
            String email,
            String senhaHash,
            TipoUsuario tipo
    ) {
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.tipo = tipo;
    }

    @PrePersist
    private void antesDeSalvar() {
        Instant agora = Instant.now();

        criado = agora;
        atualizado = agora;
    }

    @PreUpdate
    private void antesDeAtualizar() {
        atualizado = Instant.now();
    }

    public void desativar() {
        if (!ativo) {
            throw new RegraDeNegocioException("Usuário precisa estar ativo para desativar.");
        }

        ativo = false;
    }

    public void ativar() {
        if (ativo) {
            throw new RegraDeNegocioException("Usuário precisa estar desativado para ativar.");
        }

        ativo = true;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Instant getCriado() {
        return criado;
    }

    public Instant getAtualizado() {
        return atualizado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
