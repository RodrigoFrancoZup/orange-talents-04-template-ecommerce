package br.com.zupacademy.rodrigo.ecommerce.compra;

import br.com.zupacademy.rodrigo.ecommerce.annotation.ExistsId;
import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @NotNull
    @Positive
    private Integer quantidade;

   @NotNull
    private FormaDePagamento formaDePagamento;

    public CompraRequest(Long idProduto, Integer quantidade, FormaDePagamento formaDePagamento) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.formaDePagamento = formaDePagamento;
    }


    public Compra converteCompraRequestParaCompra(Produto produto, Usuario usuario) {
        return new Compra(produto, this.quantidade, usuario, this.formaDePagamento);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
