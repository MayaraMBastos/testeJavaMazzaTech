package Service;

import maymb.DTOs.AgendamentoRequestDTO;
import maymb.DTOs.AgendamentoResponseDTO;
import maymb.Exceptions.RegraDeNegocioException;
import maymb.Model.AgendamentoEntity;
import maymb.Respository.AgendamentoRepository;
import maymb.Service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @InjectMocks
    private AgendamentoService agendamentoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarAgendamentoComSucessoERetornarDTO() {
        // Cenário de teste
        AgendamentoRequestDTO requestDTO = new AgendamentoRequestDTO("1234567890", "6789012345", new BigDecimal("1000.00"), LocalDate.now(), LocalDate.now());
        AgendamentoEntity entityToSave = new AgendamentoEntity();
        entityToSave.setContaOrigem(requestDTO.getContaOrigem());
        entityToSave.setContaDestino(requestDTO.getContaDestino());
        entityToSave.setValorTransferencia(requestDTO.getValorTransferencia());
        entityToSave.setDataTransferencia(requestDTO.getDataTransferencia());

        AgendamentoEntity savedEntity = new AgendamentoEntity();
        savedEntity.setId(1L);
        savedEntity.setContaOrigem(requestDTO.getContaOrigem());
        savedEntity.setContaDestino(requestDTO.getContaDestino());
        savedEntity.setValorTransferencia(requestDTO.getValorTransferencia());
        savedEntity.setTaxa(new BigDecimal("12.00").setScale(2, RoundingMode.HALF_UP));
        savedEntity.setDataTransferencia(requestDTO.getDataTransferencia());
        savedEntity.setDataAgendamento(LocalDate.now());

        when(agendamentoRepository.save(any(AgendamentoEntity.class))).thenReturn(savedEntity);

        // Ação
        AgendamentoResponseDTO responseDTO = agendamentoService.salvarAgendamento(requestDTO);

        // Verificação
        assertNotNull(responseDTO);
        assertEquals(savedEntity.getId(), responseDTO.getId());
        assertEquals(savedEntity.getTaxa(), responseDTO.getTaxa());
        verify(agendamentoRepository, times(1)).save(any(AgendamentoEntity.class));
    }
@Test
    void deveLancarExcecaoQuandoDataTransferenciaAnteriorADataAtual() {
        // Cenário: Tentativa de agendar uma transferência com data anterior a data de agendamento.
        // Data de agendamento é hoje, data de transferência é ontem.
        AgendamentoRequestDTO requestDTO = new AgendamentoRequestDTO(
                "1234567890",
                "6789012345",
                new BigDecimal("100.00"),
                LocalDate.now().minusDays(1), // data de transferencia no passado
                LocalDate.now() // data de agendamento
        );

        // Ação e Verificação: Confirma que o método lanca a excecao correta
        assertThrows(RegraDeNegocioException.class, () -> agendamentoService.salvarAgendamento(requestDTO));
    }

       @Test
    void deveLancarExcecaoQuandoTaxaNaoAplicavel() {
        // Cenário: Valor que nao se encaixa em nenhuma regra de taxa
        AgendamentoRequestDTO requestDTO = new AgendamentoRequestDTO(
                "1234567890",
                "6789012345",
                new BigDecimal("20000.00"), // Valor que nao se encaixa
                LocalDate.now().plusDays(51),
                LocalDate.now()
        );

        // Acao e Verificacao
        assertThrows(RegraDeNegocioException.class, () -> agendamentoService.salvarAgendamento(requestDTO));
    }

    @Test
    void deveRetornarAgendamentosPorContaOrigem() {
        // Cenário
        String contaOrigem = "1234567890";
        AgendamentoEntity entity1 = new AgendamentoEntity();
        entity1.setContaOrigem(contaOrigem);
        AgendamentoEntity entity2 = new AgendamentoEntity();
        entity2.setContaOrigem(contaOrigem);
        List<AgendamentoEntity> entityList = Arrays.asList(entity1, entity2);

        when(agendamentoRepository.findByContaOrigem(contaOrigem)).thenReturn(entityList);

        // Ação
        List<AgendamentoResponseDTO> result = agendamentoService.findByContaOrigem(contaOrigem);

        // Verificação
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(contaOrigem, result.get(0).getContaOrigem());
        verify(agendamentoRepository, times(1)).findByContaOrigem(contaOrigem);
    }

    @Test
    void deveDeletarAgendamentoPorId() {
        // Cenário
        Long id = 1L;
        doNothing().when(agendamentoRepository).deleteById(id);

        // Ação
        agendamentoService.delete(id);

        // Verificação
        verify(agendamentoRepository, times(1)).deleteById(id);
    }
}

