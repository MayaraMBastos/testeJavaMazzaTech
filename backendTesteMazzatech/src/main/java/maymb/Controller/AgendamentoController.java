package maymb.Controller;

import maymb.Service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import maymb.DTOs.AgendamentoRequestDTO;
import maymb.DTOs.AgendamentoResponseDTO;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    /*Injeção de dependencia por construtor, maior testabilidade*/
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping("/agendamentos")
    public ResponseEntity<AgendamentoResponseDTO> criarAgendamento(@Valid @RequestBody AgendamentoRequestDTO request) {
        // 1. Chama o serviço para processar o agendamento
        AgendamentoResponseDTO responseDto = agendamentoService.salvarAgendamento(request);

        // 2. Retorna a resposta com o status 201 Created e o corpo do objeto salvo
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

  
}
