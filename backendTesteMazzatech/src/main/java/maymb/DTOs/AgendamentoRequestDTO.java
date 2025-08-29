package maymb.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
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
    @DecimalMin(value = "0.01", message = "O valor da transferência deve ser maior que zero.")
    private BigDecimal valorTransferencia;

    @NotNull(message = "A data da transferência não pode ser nula.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataTransferencia;

    // Construtor padrão (adicionado para o Jackson)
    public AgendamentoRequestDTO() {
    }

    // Construtor completo
    public AgendamentoRequestDTO(String contaOrigem, String contaDestino, BigDecimal valorTransferencia, LocalDate dataTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valorTransferencia = valorTransferencia;
        this.dataTransferencia = dataTransferencia;
    }

    // Getters e Setters
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
}
