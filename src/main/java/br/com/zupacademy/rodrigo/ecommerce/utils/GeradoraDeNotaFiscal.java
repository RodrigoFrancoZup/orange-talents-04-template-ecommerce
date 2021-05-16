package br.com.zupacademy.rodrigo.ecommerce.utils;

import br.com.zupacademy.rodrigo.ecommerce.compra.Compra;
import br.com.zupacademy.rodrigo.ecommerce.pagamento.Pagamento;

public class GeradoraDeNotaFiscal {

    public void geraNF(Compra compra, Pagamento pagamento){
        System.out.println("Gerando NF para " + compra.getId());
    }
}
