package com.VELOCORE.backend.models;

import com.VELOCORE.backend.enums.StatusVeiculo;
import com.VELOCORE.backend.exception.RegraDeNegocioException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "chassi", nullable = false, length = 17, unique = true)
    private String chassi;

    @Column(name = "modelo", nullable = false, length = 150)
    private String modelo;

    @Column(name = "marca", nullable = false, length = 150)
    private String marca;

    @Column(name = "ano_fabricacao", nullable = false)
    private Integer anoFabricacao;

    @Column(name = "cor", nullable = false, length = 80)
    private String cor;

    @Column(name = "preco", nullable = false, precision = 19, scale = 2)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVeiculo status = StatusVeiculo.EM_PRODUCAO;

    @Column(name = "criado", nullable = false, updatable = false)
    private Instant criado;

    @Column(name = "atualizado", nullable = false)
    private Instant atualizado;

    public Veiculo() {

    }

    public Veiculo(
            String chassi,
            String modelo,
            String marca,
            Integer anoFabricacao,
            String cor,
            BigDecimal preco,
            StatusVeiculo status
    ) {
        this.chassi = chassi;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
        this.preco = preco;
        this.status = status;
        validarPreco();
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

    public void validarPreco() {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Preço precisa ser maior que zero.");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getChassi() {
        return chassi;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public Instant getCriado() {
        return criado;
    }

    public Instant getAtualizado() {
        return atualizado;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
        validarPreco();
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }
}
