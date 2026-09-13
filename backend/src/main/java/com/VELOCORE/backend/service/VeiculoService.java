package com.VELOCORE.backend.service;

import com.VELOCORE.backend.dtos.VeiculoRequest;
import com.VELOCORE.backend.dtos.VeiculoResponse;
import com.VELOCORE.backend.exception.RecursoNaoEncontradoException;
import com.VELOCORE.backend.exception.RegraDeNegocioException;
import com.VELOCORE.backend.models.Veiculo;
import com.VELOCORE.backend.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    private Veiculo buscarEntidade(UUID id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veiculo não encontrado com ID: " + id));
    }

    @Transactional
    public VeiculoResponse cadastrar(VeiculoRequest request) {
        if (veiculoRepository.existsByChassi(request.chassi())) {
            throw new RegraDeNegocioException("Chassi já cadastrado.");
        }

        Veiculo veiculo = new Veiculo(
                request.chassi(),
                request.modelo(),
                request.marca(),
                request.anoFabricacao(),
                request.cor(),
                request.preco(),
                request.status()
        );

        return VeiculoResponse.from(veiculoRepository.save(veiculo));
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponse> listar() {
        return veiculoRepository.findAll()
                .stream().map(VeiculoResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public VeiculoResponse buscarPorId(UUID id) {
        return VeiculoResponse.from(buscarEntidade(id));
    }
}
