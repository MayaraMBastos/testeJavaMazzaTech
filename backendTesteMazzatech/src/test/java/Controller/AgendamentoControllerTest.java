package Controller;
import maymb.Controller.AgendamentoController;
import maymb.DTOs.AgendamentoRequestDTO;
import maymb.DTOs.AgendamentoResponseDTO;
import maymb.Service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AgendamentoControllerTest {

    @Mock
    private AgendamentoService agendamentoService;

    @InjectMocks
    private AgendamentoController agendamentoController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarAgendamentoComSucesso() {
        // 1. Cenário
        AgendamentoRequestDTO requestDTO = new AgendamentoRequestDTO("1234567890", "6789012345", new BigDecimal("1000.00"), LocalDate.now());
        AgendamentoResponseDTO responseDTO = new AgendamentoResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setContaOrigem("1234567890");
        responseDTO.setContaDestino("6789012345");
        responseDTO.setValorTransferencia(new BigDecimal("1000.00"));
        responseDTO.setTaxa(new BigDecimal("30.00"));
        responseDTO.setDataTransferencia(LocalDate.now());
        responseDTO.setDataAgendamento(LocalDate.now());

        when(agendamentoService.salvarAgendamento(any(AgendamentoRequestDTO.class))).thenReturn(responseDTO);

        // 2. Ação
        ResponseEntity<AgendamentoResponseDTO> response = agendamentoController.criarAgendamento(requestDTO);

        // 3. Verificação
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(agendamentoService, times(1)).salvarAgendamento(requestDTO);
    }

    @Test
    void deveRetornarAgendamentosPorContaOrigemComSucesso() {
        // 1. Cenário
        String contaOrigem = "1234567890";
        AgendamentoResponseDTO agendamento1 = new AgendamentoResponseDTO();
        agendamento1.setContaOrigem(contaOrigem);
        AgendamentoResponseDTO agendamento2 = new AgendamentoResponseDTO();
        agendamento2.setContaOrigem(contaOrigem);
        List<AgendamentoResponseDTO> agendamentos = Arrays.asList(agendamento1, agendamento2);

        when(agendamentoService.findByContaOrigem(contaOrigem)).thenReturn(agendamentos);

        // 2. Ação
        ResponseEntity<List<AgendamentoResponseDTO>> response = agendamentoController.getAgendamentosPorContaOrigem(contaOrigem);

        // 3. Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(agendamentos, response.getBody());
        verify(agendamentoService, times(1)).findByContaOrigem(contaOrigem);
    }

    @Test
    void deveDeletarAgendamentoComSucesso() {
        // 1. Cenário
        Long id = 1L;
        doNothing().when(agendamentoService).delete(id);

        // 2. Ação
        ResponseEntity<Void> response = agendamentoController.deleteAgendamento(id);

        // 3. Verificação
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(agendamentoService, times(1)).delete(id);
    }
}
