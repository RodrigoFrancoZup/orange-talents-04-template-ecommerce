package br.com.zupacademy.rodrigo.ecommerce.imagem;

import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;

import javax.persistence.*;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;

    @ManyToOne
    private Produto produto;

    public Imagem(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    @Deprecated
    public Imagem() {
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public Produto getProduto() {
        return produto;
    }
}
