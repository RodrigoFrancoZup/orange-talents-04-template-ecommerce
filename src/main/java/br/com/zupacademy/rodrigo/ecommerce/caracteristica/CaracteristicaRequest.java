package br.com.zupacademy.rodrigo.ecommerce.caracteristica;

import br.com.zupacademy.rodrigo.ecommerce.annotation.UniqueValue;
import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class CaracteristicaRequest {
 /*
    Essa validação n aplica-se em nosso cenário,
    seria para a tabela toda! Foi só um teste para ver o @valid funcionando em cima de um List<>
    @UniqueValue(domainClass = Caracteristica.class, fieldName = "nome")
 */
    private String nome;
    private String descricao;

    public CaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    private Caracteristica converteCaracteristicaRequestParaCaracteristica(Produto produto) {
        return new Caracteristica(this.nome, this.descricao, produto);
    }

    public static List<Caracteristica> converteCaracteristicaRequestParaCaracteristica(List<CaracteristicaRequest> caracteristicasRequest, Produto produto) {
        List<Caracteristica> caracteristicas = new ArrayList<>();
        for (CaracteristicaRequest aux : caracteristicasRequest) {
            Caracteristica caracteristica = aux.converteCaracteristicaRequestParaCaracteristica(produto);
            caracteristicas.add(caracteristica);
        }
        return caracteristicas;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
