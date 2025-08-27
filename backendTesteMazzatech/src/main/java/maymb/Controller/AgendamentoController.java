package maymb.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import maymb.DTOs.AgendamentoRequestDTO;
import maymb.DTOs.AgendamentoResponseDTO;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @PostMapping
    public AgendamentoResponseDTO criarAgendamento(AgendamentoRequestDTO request) {
        // Lógica para criar um agendamento
        return new AgendamentoResponseDTO(
                request.getContaOrigem(),
                request.getContaDestino(),
                request.getValorTransferencia(),
                request.getDataTransferencia()
        );
    }

    public List<AgendamentoResponseDTO> listarAgendamentos() {
        // Lógica para listar agendamentos
        return List.of();
    }
}
