package maymb.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "agendamentos_tb")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contaOrigem;

    private String contaDestino;

    private BigDecimal valorTransferencia;

    private BigDecimal taxa;

    private LocalDate dataTransferencia;

    private LocalDate dataAgendamento;

    public AgendamentoEntity(LocalDate dataAgendamento, LocalDate dataTransferencia, BigDecimal taxa, BigDecimal valorTransferencia, String contaDestino, String contaOrigem, Long id) {
        this.dataAgendamento = dataAgendamento;
        this.dataTransferencia = dataTransferencia;
        this.taxa = taxa;
        this.valorTransferencia = valorTransferencia;
        this.contaDestino = contaDestino;
        this.contaOrigem = contaOrigem;
        this.id = id;
    }

    public AgendamentoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
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
