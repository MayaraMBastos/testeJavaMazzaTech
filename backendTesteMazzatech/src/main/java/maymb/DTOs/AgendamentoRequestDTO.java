package maymb.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AgendamentoRequestDTO {

    @NotBlank(message = "A conta de origem não pode estar em branco.")
    @Pattern(regexp = "\\d{10}", message = "A conta de origem deve conter exatamente 10 dígitos.")
    private String contaOrigem;

    @NotBlank(message = "A conta de destino não pode estar em branco.")
    @Pattern(regexp = "\\d{10}", message = "A conta de destino deve conter exatamente 10 dígitos.")
    private String contaDestino;

    @NotNull(message = "O valor da transferência não pode ser nulo.")
    private BigDecimal valorTransferencia;

    @NotNull(message = "A data da transferência não pode ser nula.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataTransferencia;

    @NotNull(message = "A data de agendamento não pode ser nula.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataAgendamento;

    public AgendamentoRequestDTO() {
    }

    // Construtor, getters e setters
    public AgendamentoRequestDTO(String contaOrigem, String contaDestino, BigDecimal valorTransferencia, LocalDate dataTransferencia, LocalDate dataAgendamento) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valorTransferencia = valorTransferencia;
        this.dataTransferencia = dataTransferencia;
        this.dataAgendamento = dataAgendamento;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(BigDecimal valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
