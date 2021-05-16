package br.com.zupacademy.rodrigo.ecommerce.pagamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoResponsePagseguro {

    @NotNull
    private Long id;

    @NotBlank
    private String status;

    public PagamentoResponsePagseguro(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
