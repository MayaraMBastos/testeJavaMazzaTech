
package maymb.DTOs;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AgendamentoRequestDTO {

    @NotBlank
    private String contaOrigem;

    @NotBlank
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
