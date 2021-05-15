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
    public ResponseEntity<?> compra(@RequestBody @Valid CompraRequest compraRequest, @AuthenticationPrincipal Usuario usuario) throws URISyntaxException {

        Produto produto = produtoRepository.findById(compraRequest.getIdProduto()).get();
        Boolean estoqueAbatido = produto.reduzEstoque(compraRequest.getQuantidade());

        if (estoqueAbatido) {
            Compra compra = compraRequest.converteCompraRequestParaCompra(produto, usuario);
            compraRepository.save(compra);

            URI uri = new URI(compra.getFormaDePagamento().direcionaParaGateway());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uri);
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }

        return ResponseEntity.badRequest().body("Estoque insuficiente!");
    }
}
