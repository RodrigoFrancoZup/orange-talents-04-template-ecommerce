package br.com.zupacademy.rodrigo.ecommerce.categoria;

import br.com.zupacademy.rodrigo.ecommerce.annotation.ExistsId;
import br.com.zupacademy.rodrigo.ecommerce.annotation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public CategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria converteCategoriaRequestParaCategoria(CategoriaRepository categoriaRepository){
        Categoria categoria = new Categoria(this.nome);
        if(this.idCategoriaMae != null){
            Categoria categoriaMae = categoriaRepository.findById(this.idCategoriaMae).get();
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
