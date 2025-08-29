
package maymb.DTOs;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AgendamentoRequestDTO {
    private static final String CONTA_PATTERN = "^\\d{10}$";
    @NotBlank
    @Pattern(regexp = CONTA_PATTERN, message = "A conta de origem deve ter 10 digitos.")
    private String contaOrigem;

    @NotBlank
    @Pattern(regexp = CONTA_PATTERN, message = "A conta de destino deve ter 10 digitos.")
    private String contaDestino;

    @NotNull
    private BigDecimal valorTransferencia;

    @NotNull
    private LocalDate dataTransferencia;

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
