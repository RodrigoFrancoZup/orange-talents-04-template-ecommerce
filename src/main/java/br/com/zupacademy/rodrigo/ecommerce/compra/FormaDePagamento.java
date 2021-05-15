package br.com.zupacademy.rodrigo.ecommerce.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum FormaDePagamento {

    Paypal {
        @Override
        public String direcionaParaGateway(UriComponentsBuilder uriBuilder, Compra compra) {
            String retorno = uriBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();
            return "paypal.com/" + compra.getId() + "?redirectUrl=" + retorno;
        }
    },
    Pagseguro {
        @Override
        public String direcionaParaGateway(UriComponentsBuilder uriBuilder, Compra compra) {
            String retorno = uriBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toString();
            return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + retorno;
        }
    };

    public abstract String direcionaParaGateway(UriComponentsBuilder uriBuilder, Compra compra);
}
