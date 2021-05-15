package br.com.zupacademy.rodrigo.ecommerce.produto;


import br.com.zupacademy.rodrigo.ecommerce.caracteristica.Caracteristica;
import br.com.zupacademy.rodrigo.ecommerce.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.rodrigo.ecommerce.categoria.Categoria;
import br.com.zupacademy.rodrigo.ecommerce.imagem.Imagem;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicas = new ArrayList<>();
    private String descricao;

    @ManyToOne
    private Categoria categoria;
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Imagem> imagens = new HashSet<>();

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria, List<CaracteristicaRequest> caracteristicasRequest) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = CaracteristicaRequest.converteCaracteristicaRequestParaCaracteristica(caracteristicasRequest, this);
    }

    @Deprecated
    public Produto() {

    }

    //Vinculo de produto com imagem e vice-versa!
    public void adicionaImagem(Set<String> links) {
        for (String aux : links) {
            Imagem imagem = new Imagem(aux, this);
            this.imagens.add(imagem);
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }

    public Boolean reduzEstoque(Integer quantidadeVendida) {
        if (this.quantidade >= quantidadeVendida) {
            this.quantidade -= quantidadeVendida;
            return true;
        }
        return false;
    }
}
