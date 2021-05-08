package br.com.zupacademy.rodrigo.ecommerce.opiniao;

import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/opiniao")
public class OpiniaoController {

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/produto/{id}")
    public ResponseEntity<OpiniaoResponse> cadastra(@RequestBody @Valid OpiniaoRequest opiniaoRequest, @PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRepository.findById(id).get();
        Opiniao opiniao = opiniaoRequest.converteOpiniaoRequestParaOpiniao(usuario, produto);
        opiniaoRepository.save(opiniao);
        return ResponseEntity.ok(new OpiniaoResponse(opiniao));
    }
}
