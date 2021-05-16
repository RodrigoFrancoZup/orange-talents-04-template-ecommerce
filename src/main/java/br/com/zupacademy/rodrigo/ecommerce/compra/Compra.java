package br.com.zupacademy.rodrigo.ecommerce.compra;

import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Produto produto;
    private Integer quantidade;

    @ManyToOne
    private Usuario usuario;
    private BigDecimal precoPagoNoProduto;

    @Enumerated(EnumType.STRING)
    private StatusCompra status = StatusCompra.INICIADA;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    @Deprecated
    public Compra() {
    }

    public Compra(Produto produto, Integer quantidade, Usuario usuario, FormaDePagamento formaDePagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.formaDePagamento = formaDePagamento;
        this.precoPagoNoProduto = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public BigDecimal getPrecoPagoNoProduto() {
        return precoPagoNoProduto;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setStatus(StatusCompra status) {
        this.status = status;
    }
}
