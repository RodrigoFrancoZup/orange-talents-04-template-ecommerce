package br.com.zupacademy.rodrigo.ecommerce.compra;


import br.com.zupacademy.rodrigo.ecommerce.init.binder.VerificaEstoqueSuficienteValidator;
import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("compra")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VerificaEstoqueSuficienteValidator verificaEstoqueSuficienteValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(verificaEstoqueSuficienteValidator);
    }

    @PostMapping
    public ResponseEntity<?> compra(@RequestBody @Valid CompraRequest compraRequest, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriBuilder) {

        Produto produto = produtoRepository.findById(compraRequest.getIdProduto()).get();
        Boolean estoqueAbatido = produto.reduzEstoque(compraRequest.getQuantidade());
        //Não precisamos dar save no produto para refuzir o estoque.
        // Lembre-se ao dar um findById o objeto Produto está Managed! Gerenciado pelo Hibernate!

        if (estoqueAbatido) {
            Compra compra = compraRequest.converteCompraRequestParaCompra(produto, usuario);

            compraRepository.save(compra);

            System.out.println("Enviando e-mail para o vendedor: " + compra.getUsuario().getUsername());

            String url = compra.getFormaDePagamento().direcionaParaGateway(uriBuilder, compra);
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(url);
        }

        return ResponseEntity.badRequest().body("Estoque insuficiente!");
    }
}
