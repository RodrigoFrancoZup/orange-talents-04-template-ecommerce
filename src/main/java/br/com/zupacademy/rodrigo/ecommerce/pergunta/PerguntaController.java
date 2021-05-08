package br.com.zupacademy.rodrigo.ecommerce.pergunta;

import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/produto/{id}")
    @Transactional
    public ResponseEntity<PerguntaResponse> cadastra(@RequestBody @Valid PerguntaRequest perguntaRequest, @AuthenticationPrincipal Usuario usuario, @PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).get();
        Pergunta pergunta = perguntaRequest.convertePerguntRequestParaPergunta(usuario, produto);
        perguntaRepository.save(pergunta);
        System.out.println("Enviando email para: " + pergunta.getProduto().getUsuario().getLogin());
        return ResponseEntity.ok(new PerguntaResponse(pergunta));
    }
}
