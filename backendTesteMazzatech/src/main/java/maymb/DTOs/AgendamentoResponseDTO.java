package maymb.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AgendamentoResponseDTO {

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valorTransferencia;
    private LocalDate dataTransferencia;

    public AgendamentoResponseDTO(String contaOrigem, String contaDestino, BigDecimal valorTransferencia, LocalDate dataTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valorTransferencia = valorTransferencia;
        this.dataTransferencia = dataTransferencia;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public BigDecimal getValorTransferencia() {
        return valorTransferencia;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }
}
