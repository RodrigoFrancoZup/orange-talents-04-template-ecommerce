package br.com.zupacademy.rodrigo.ecommerce.pergunta;


import br.com.zupacademy.rodrigo.ecommerce.produto.ProdutoResponse;
import br.com.zupacademy.rodrigo.ecommerce.usuario.UsuarioResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"produto"})
public class PerguntaResponse {

    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime instanteCriacao = LocalDateTime.now();
    private UsuarioResponse usuario;
    private ProdutoResponse produto;

    public PerguntaResponse(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.titulo = pergunta.getTitulo();
        this.conteudo = pergunta.getConteudo();
        this.instanteCriacao = pergunta.getInstanteCriacao();
        this.usuario = new UsuarioResponse(pergunta.getUsuario());
        this.produto = new ProdutoResponse(pergunta.getProduto());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public ProdutoResponse getProduto() {
        return produto;
    }
}
