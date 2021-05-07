package br.com.zupacademy.rodrigo.ecommerce.caracteristica;

import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;

import javax.persistence.*;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @ManyToOne
    private Produto produto;

    public Caracteristica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Deprecated
   public Caracteristica() {
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto getProduto() {
        return produto;
    }
}
