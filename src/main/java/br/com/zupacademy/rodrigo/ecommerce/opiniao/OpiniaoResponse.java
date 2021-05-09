package br.com.zupacademy.rodrigo.ecommerce.opiniao;


import br.com.zupacademy.rodrigo.ecommerce.produto.ProdutoResponse;
import br.com.zupacademy.rodrigo.ecommerce.usuario.UsuarioResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"produto"})
public class OpiniaoResponse {

    private Long id;
    private Integer nota;
    private String titulo;
    private String descricao;
    private UsuarioResponse usuario;
    private ProdutoResponse produto;

    public OpiniaoResponse(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usuario = new UsuarioResponse(opiniao.getUsuario());
        this.produto = new ProdutoResponse(opiniao.getProduto());
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public ProdutoResponse getProduto() {
        return produto;
    }
}
