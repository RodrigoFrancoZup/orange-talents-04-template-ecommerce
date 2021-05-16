package br.com.zupacademy.rodrigo.ecommerce.utils;

import br.com.zupacademy.rodrigo.ecommerce.compra.Compra;

public class RankingDeVendedores {

    public void avaliaVendedor(Compra compra) {
        System.out.println("Avaliando o vendedor: " + compra.getProduto().getUsuario().getUsername());
    }
}
