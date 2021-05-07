package br.com.zupacademy.rodrigo.ecommerce.caracteristica;

import br.com.zupacademy.rodrigo.ecommerce.produto.ProdutoResponse;

import java.util.ArrayList;
import java.util.List;

public class CaracteristicaResponse {

    private Long id;
    private String nome;
    private String descricao;


    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.id = caracteristica.getId();
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();

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

    public static List<CaracteristicaResponse> converteListaDeCaracteristicaParaCaracteristicaResponse(List<Caracteristica> caracteristicas) {
        List<CaracteristicaResponse> caracteristicasResponse = new ArrayList<>();
        for (Caracteristica aux : caracteristicas) {
            CaracteristicaResponse caracteristicaResponse = new CaracteristicaResponse(aux);
            caracteristicasResponse.add(caracteristicaResponse);
        }
        return caracteristicasResponse;
    }

}
