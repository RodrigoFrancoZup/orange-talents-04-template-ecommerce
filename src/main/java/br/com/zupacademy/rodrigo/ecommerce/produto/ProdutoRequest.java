package br.com.zupacademy.rodrigo.ecommerce.produto;

import br.com.zupacademy.rodrigo.ecommerce.annotation.ExistsId;
import br.com.zupacademy.rodrigo.ecommerce.caracteristica.Caracteristica;
import br.com.zupacademy.rodrigo.ecommerce.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.rodrigo.ecommerce.categoria.Categoria;
import br.com.zupacademy.rodrigo.ecommerce.categoria.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Min(1)
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private Integer quantidade;

    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequest> caracteristicas;

    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade, List<CaracteristicaRequest> caracteristicas, String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public Produto converteProdutoRequestParaProduto(CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.findById(this.idCategoria).get();
        return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria, this.caracteristicas);
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }
}
