package br.com.zupacademy.rodrigo.ecommerce.utils;

import br.com.zupacademy.rodrigo.ecommerce.compra.Compra;
import br.com.zupacademy.rodrigo.ecommerce.pagamento.Pagamento;

public class EnvioDeEmail {

    public void envioEmailVendedor(Compra compra){
        System.out.println("Enviando email para o vendedor" + compra.getProduto().getUsuario());
    }

    public void envioEmailComprador(Pagamento pagamento){
        System.out.println("Enviando email para o comprador" + pagamento.getCompra().getUsuario());
    }
}
