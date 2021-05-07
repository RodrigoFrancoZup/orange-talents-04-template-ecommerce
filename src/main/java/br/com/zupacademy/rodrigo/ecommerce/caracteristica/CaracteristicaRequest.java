package br.com.zupacademy.rodrigo.ecommerce.caracteristica;

import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.produto.ProdutoRequest;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class CaracteristicaRequest {

    private String nome;
    private String descricao;

    public CaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    private Caracteristica converteCaracteristicaRequestParaCaracteristica() {
        return new Caracteristica(this.nome, this.descricao);
    }

    public static List<Caracteristica> converteCaracteristicaRequestParaCaracteristica(List<CaracteristicaRequest> caracteristicasRequest) {
        List<Caracteristica> caracteristicas = new ArrayList<>();
        for (CaracteristicaRequest aux : caracteristicasRequest) {
            Caracteristica caracteristica = aux.converteCaracteristicaRequestParaCaracteristica();
            caracteristicas.add(caracteristica);
        }
        return caracteristicas;
    }
}
