package maymb.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AgendamentoResponseDTO {

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valorTransferencia;
    private LocalDate dataTransferencia;

    private LocalDate dataAgendamento;

    private BigDecimal taxa;

    public AgendamentoResponseDTO(String contaOrigem, String contaDestino, BigDecimal valorTransferencia, LocalDate dataTransferencia, LocalDate dataAgendamento, BigDecimal taxa) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valorTransferencia = valorTransferencia;
        this.dataTransferencia = dataTransferencia;
        this.dataAgendamento = dataAgendamento;
        this.taxa = taxa;
    }

    public AgendamentoResponseDTO() {
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public void setValorTransferencia(BigDecimal valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
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
