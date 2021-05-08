package br.com.zupacademy.rodrigo.ecommerce.pergunta;

import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    public Pergunta(String titulo, String conteudo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Deprecated
    public Pergunta() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getConteudo() {
        return conteudo;
    }
}
