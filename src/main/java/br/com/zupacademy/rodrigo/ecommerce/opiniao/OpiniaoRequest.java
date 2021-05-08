package br.com.zupacademy.rodrigo.ecommerce.opiniao;


import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class OpiniaoRequest {

    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public OpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao converteOpiniaoRequestParaOpiniao(Usuario usuario, Produto produto) {
        return new Opiniao(this.nota, this.titulo, this.descricao, usuario, produto);
    }
}
