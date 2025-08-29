package maymb.Controller;

import maymb.Respository.AgendamentoRepository;
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

    @GetMapping(value = "/agendamentos", params = "contaOrigem")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentosPorContaOrigem(@RequestParam String contaOrigem) {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.findByContaOrigem(contaOrigem);
        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping("/agendamentos")
    public ResponseEntity<AgendamentoResponseDTO> criarAgendamento(@Valid @RequestBody AgendamentoRequestDTO request) {
        // 1. Chama o serviço para processar o agendamento
        AgendamentoResponseDTO responseDto = agendamentoService.salvarAgendamento(request);

        // 2. Retorna a resposta com o status 201 Created e o corpo do objeto salvo
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/agendamentos/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        agendamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

  
}
