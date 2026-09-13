package com.VELOCORE.backend.dtos;

import com.VELOCORE.backend.enums.StatusVeiculo;
import com.VELOCORE.backend.models.Veiculo;

import java.math.BigDecimal;
import java.util.UUID;

public record VeiculoResponse(
        UUID id,
        String chassi,
        String modelo,
        String marca,
        Integer anoFabricacao,
        String cor,
        BigDecimal preco,
        StatusVeiculo status
) {
    public static VeiculoResponse from(Veiculo veiculo) {
        return new VeiculoResponse(
                veiculo.getId(),
                veiculo.getChassi(),
                veiculo.getModelo(),
                veiculo.getMarca(),
                veiculo.getAnoFabricacao(),
                veiculo.getCor(),
                veiculo.getPreco(),
                veiculo.getStatus()
        );
    }
}
