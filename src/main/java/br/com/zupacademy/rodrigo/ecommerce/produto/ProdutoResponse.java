package br.com.zupacademy.rodrigo.ecommerce.produto;

import br.com.zupacademy.rodrigo.ecommerce.caracteristica.CaracteristicaResponse;


import br.com.zupacademy.rodrigo.ecommerce.categoria.CategoriaResponse;
import br.com.zupacademy.rodrigo.ecommerce.imagem.Imagem;
import br.com.zupacademy.rodrigo.ecommerce.usuario.UsuarioResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private List<CaracteristicaResponse> caracteristicas;
    private String descricao;
    private CategoriaResponse categoria;
    private LocalDateTime instanteCriacao;
    private UsuarioResponse usuario;
    private List<String> linksImagens = new ArrayList<>();

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.caracteristicas = CaracteristicaResponse.converteListaDeCaracteristicaParaCaracteristicaResponse(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.instanteCriacao = produto.getInstanteCriacao();
        this.usuario = new UsuarioResponse(produto.getUsuario());
        this.categoria = new CategoriaResponse(produto.getCategoria());
        for(Imagem imagem : produto.getImagens()){
            this.linksImagens.add(imagem.getLink());
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }
}
