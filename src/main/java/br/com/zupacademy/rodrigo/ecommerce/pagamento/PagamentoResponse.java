package br.com.zupacademy.rodrigo.ecommerce.pagamento;

public class PagamentoResponse {

    private Long id;
    private Long idCompra;
    private StatusPagamento status;

    public PagamentoResponse(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.idCompra = pagamento.getCompra().getId();
        this.status = pagamento.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public StatusPagamento getStatus() {
        return status;
    }
}
