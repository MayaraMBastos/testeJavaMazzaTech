package maymb.Respository;

import maymb.DTOs.AgendamentoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maymb.Model.AgendamentoEntity;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    List<AgendamentoEntity> findByContaOrigem(String contaOrigem);
}
