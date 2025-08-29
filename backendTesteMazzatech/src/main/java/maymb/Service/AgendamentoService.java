package maymb.Service;

import maymb.DTOs.AgendamentoRequestDTO;
import maymb.DTOs.AgendamentoResponseDTO;
import maymb.Model.AgendamentoEntity;
import maymb.Respository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AgendamentoService {

    private AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public AgendamentoResponseDTO salvarAgendamento(AgendamentoRequestDTO requestDTO) {
        // Passo 1: Validação da data
        verificarDataValida(requestDTO.getDataTransferencia());

        // Passo 2: Cálculo dos dias entre as datas
        long diasParaTransferencia = calcularDiasEntreDatas(requestDTO.getDataTransferencia());

        // Passo 3: Cálculo da taxa
        BigDecimal taxaCalculada = calcularTaxa(requestDTO.getValorTransferencia(), diasParaTransferencia);

        // Passo 4: Verifica se a taxa existe (conforme regra do teste)
        if (taxaCalculada == null) {
            throw new IllegalArgumentException("Nao ha taxa aplicavel para essa transferencia.");
        }

        // Passo 5: Conversão e preenchimento da entidade
        AgendamentoEntity entity = convertToEntity(requestDTO);
        entity.setTaxa(taxaCalculada);
        entity.setDataAgendamento(LocalDate.now());

        // Passo 6: Persistência no banco de dados
        AgendamentoEntity savedEntity = agendamentoRepository.save(entity);

        // Passo 7: Conversão da entidade salva para DTO de resposta
        return convertToResponseDTO(savedEntity);
    }
    private void verificarDataValida(LocalDate dataTransferencia) {
        if (dataTransferencia.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da transferência não pode ser anterior à data de agendamento.");
        }
    }

    // Função de responsabilidade única para o cálculo de dias
    private long calcularDiasEntreDatas(LocalDate dataTransferencia) {
        return ChronoUnit.DAYS.between(LocalDate.now(), dataTransferencia);
    }
    public BigDecimal calcularTaxa(BigDecimal valorTransferencia, long dias) {

        // Regra A: Transferência no mesmo dia (dias = 0)
        if (dias == 0) {
            BigDecimal taxaFixa = new BigDecimal("3.00");
            BigDecimal taxaPercentual = valorTransferencia.multiply(new BigDecimal("0.025"));
            return taxaFixa.add(taxaPercentual).setScale(2, RoundingMode.HALF_UP);
        }

        // Regra B: Até 10 dias
        if (dias >= 1 && dias <= 10) {
            return new BigDecimal("12.00").setScale(2, RoundingMode.HALF_UP);
        }

        // Verifica as regras para valores acima de R$25.000,00
        if (valorTransferencia.compareTo(new BigDecimal("25000")) > 0) {
            // Regra G: Acima de R$25.000,00 e mais de 50 dias
            if (dias > 50) {
                return valorTransferencia.multiply(new BigDecimal("0.012")).setScale(2, RoundingMode.HALF_UP);
            }
        }
        // Aplica as regras C, D, E e F
        else {
            if (dias >= 11 && dias <= 20) {
                return valorTransferencia.multiply(new BigDecimal("0.082")).setScale(2, RoundingMode.HALF_UP);
            }
            if (dias >= 21 && dias <= 30) {
                return valorTransferencia.multiply(new BigDecimal("0.069")).setScale(2, RoundingMode.HALF_UP);
            }
            if (dias >= 31 && dias <= 40) {
                return valorTransferencia.multiply(new BigDecimal("0.047")).setScale(2, RoundingMode.HALF_UP);
            }
            if (dias >= 41 && dias <= 50) {
                return valorTransferencia.multiply(new BigDecimal("0.017")).setScale(2, RoundingMode.HALF_UP);
            }
        }

        // Caso não haja taxa aplicável, retorne 'null' conforme o requisito
        return null;
    }
    private AgendamentoEntity convertToEntity(AgendamentoRequestDTO dto) {
        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setContaOrigem(dto.getContaOrigem());
        entity.setContaDestino(dto.getContaDestino());
        entity.setValorTransferencia(dto.getValorTransferencia());
        entity.setDataTransferencia(dto.getDataTransferencia());
        return entity;
    }

    private AgendamentoResponseDTO convertToResponseDTO(AgendamentoEntity entity) {
        AgendamentoResponseDTO response = new AgendamentoResponseDTO();
        response.setContaOrigem(entity.getContaOrigem());
        response.setContaDestino(entity.getContaDestino());
        response.setValorTransferencia(entity.getValorTransferencia());
        response.setTaxa(entity.getTaxa()); // Pega a taxa da entidade já persistida
        response.setDataTransferencia(entity.getDataTransferencia());
        response.setDataTransferencia(entity.getDataAgendamento()); // Corrigido
        return response;
    }


}
