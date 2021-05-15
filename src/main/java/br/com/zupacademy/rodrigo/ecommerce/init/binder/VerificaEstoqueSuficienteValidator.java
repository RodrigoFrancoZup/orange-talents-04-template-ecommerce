package br.com.zupacademy.rodrigo.ecommerce.init.binder;

import br.com.zupacademy.rodrigo.ecommerce.compra.CompraRequest;
import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class VerificaEstoqueSuficienteValidator implements Validator {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        CompraRequest request = (CompraRequest) target;
        Produto produto = produtoRepository.findById(request.getIdProduto()).get();
        Integer quantidadeEmEstoque = produto.getQuantidade();

        if(!(quantidadeEmEstoque >= request.getQuantidade())){
            errors.rejectValue("quantidade", null, "Estoque insuficiente! Estoque disponível desse produto é de: " + quantidadeEmEstoque);
        }

    }
}
