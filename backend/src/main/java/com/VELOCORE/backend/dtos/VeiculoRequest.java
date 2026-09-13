package com.VELOCORE.backend.dtos;

import com.VELOCORE.backend.enums.StatusVeiculo;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record VeiculoRequest(
        @NotBlank(message = "Chassi é obrigatório")
        @Size(
                min = 17,
                max = 17,
                message = "Chassi deve ter 17 caracteres."
        )
        String chassi,

        @NotBlank(
                message = "Modelo é obrigatório"
        )
        @Size(max = 150, message = "Modelo deve conter no máximo 150 caracteres.")
        String modelo,

        @NotBlank(message = "Marca é obrigatória")
        @Size(max = 150, message = "Marca deve conter no máximo 150 caracteres.")
        String marca,

        @NotNull(message = "Ano de fabricação é obrigatório")
        @Positive(message = "Ano de frabricação precisa ser positvo")
        Integer anoFabricacao,

        @NotBlank(message = "Cor é obrigatória")
        @Size(max = 80, message = "Cor deve conter no máximo 80 caracteres")
        String cor,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.01", message = "Preço precisa ser maior que zero.")
        BigDecimal preco,

        @NotNull(message = "Status é obrigatório")
        StatusVeiculo status
) {
}
