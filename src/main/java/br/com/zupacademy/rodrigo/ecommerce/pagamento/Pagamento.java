package br.com.zupacademy.rodrigo.ecommerce.pagamento;

import br.com.zupacademy.rodrigo.ecommerce.compra.Compra;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime instantePagamento = LocalDateTime.now();

    @OneToOne
    private Compra compra;

    @Enumerated(value = EnumType.STRING)
    private StatusPagamento status;

    public Pagamento() {
    }

    public Pagamento( Compra compra, StatusPagamento status) {
        this.compra = compra;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstantePagamento() {
        return instantePagamento;
    }

    public Compra getCompra() {
        return compra;
    }

    public StatusPagamento getStatus() {
        return status;
    }
}
