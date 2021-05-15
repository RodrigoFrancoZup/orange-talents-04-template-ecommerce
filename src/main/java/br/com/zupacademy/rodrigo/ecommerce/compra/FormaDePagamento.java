package br.com.zupacademy.rodrigo.ecommerce.compra;

public enum FormaDePagamento {

    Paypal{
        @Override
        public String direcionaParaGateway() {
            return "paypal.com";
        }
    },
    Pagseguro{
        @Override
        public String direcionaParaGateway() {
            return "pagseguro.com";
        }
    };

    public abstract String direcionaParaGateway();
}
