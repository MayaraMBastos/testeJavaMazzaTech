package maymb.Service;

import maymb.DTOs.AgendamentoRequestDTO;
import maymb.DTOs.AgendamentoResponseDTO;
import maymb.Respository.AgendamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public AgendamentoResponseDTO salvarAgendamento(AgendamentoRequestDTO requestDTO){
        AgendamentoResponseDTO responseDTO = new AgendamentoResponseDTO();
        return responseDTO;
    }
}
