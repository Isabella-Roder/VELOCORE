package com.VELOCORE.backend.repository;

import com.VELOCORE.backend.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

    Optional<Veiculo> findByChassi(String chassi);

    boolean existsByChassi(String chassi);
}
