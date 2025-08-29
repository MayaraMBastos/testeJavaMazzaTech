package maymb.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Busca agendamentos por conta de origem",
            description = "Retorna todos os agendamentos de transferência realizados a partir da conta de origem informada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de agendamentos encontrada",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AgendamentoResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Nenhum agendamento encontrado para a conta informada")
    })
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentosPorContaOrigem(
            @Parameter(description = "Número da conta de origem", required = true, example = "1234567890")
            @RequestParam String contaOrigem
    ) {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.findByContaOrigem(contaOrigem);
        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping("/agendamentos")
    @Operation(
            summary = "Cria um novo agendamento",
            description = "Cria um novo agendamento de transferência, calculando a taxa aplicável com base nas regras de negócio."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgendamentoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida ou sem taxa aplicável")
    })
    public ResponseEntity<AgendamentoResponseDTO> criarAgendamento(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(   // <- aqui é do OpenAPI
                    description = "Dados do agendamento a ser criado",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgendamentoRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "exemploAgendamento",
                                    summary = "Exemplo de Agendamento Válido",
                                    value = "{\"contaOrigem\":\"1234567890\",\"contaDestino\":\"6789012345\",\"valorTransferencia\":1000.00,\"dataTransferencia\":\"2025-09-01\"}"
                            )
                    )
            )
            @Valid @RequestBody AgendamentoRequestDTO request   // <- este sim é o do Spring MVC
    ) {
        AgendamentoResponseDTO responseDto = agendamentoService.salvarAgendamento(request);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/agendamentos/{id}")
    @Operation(
            summary = "Exclui um agendamento",
            description = "Exclui permanentemente um agendamento de transferência a partir do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado para o ID informado")
    })
    public ResponseEntity<Void> deleteAgendamento(
            @Parameter(description = "ID do agendamento a ser excluído", required = true, example = "1")
            @PathVariable Long id
    ) {
        agendamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
