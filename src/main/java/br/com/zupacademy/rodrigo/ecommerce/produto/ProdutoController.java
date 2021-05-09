package br.com.zupacademy.rodrigo.ecommerce.produto;


import br.com.zupacademy.rodrigo.ecommerce.caracteristica.CaracteristicaRepository;
import br.com.zupacademy.rodrigo.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.rodrigo.ecommerce.imagem.ImagemRequest;
import br.com.zupacademy.rodrigo.ecommerce.opiniao.Opiniao;
import br.com.zupacademy.rodrigo.ecommerce.opiniao.OpiniaoRepository;
import br.com.zupacademy.rodrigo.ecommerce.pergunta.Pergunta;
import br.com.zupacademy.rodrigo.ecommerce.pergunta.PerguntaRepository;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import br.com.zupacademy.rodrigo.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoResponse> cadastra(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario) {

        Produto produto = produtoRequest.converteProdutoRequestParaProduto(categoriaRepository);
        produto.setUsuario(usuario);

        produtoRepository.save(produto);

        return ResponseEntity.ok(new ProdutoResponse(produto));

    }

    @PatchMapping("/{id}/adicionaImagem")
    @Transactional
    public ResponseEntity<ProdutoResponse> adicionaImagem(@Valid ImagemRequest imagemRequest, @PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRepository.findById(id).get();

        if (!produto.getUsuario().equals(usuario)) {
            return ResponseEntity.badRequest().build();
        }
        /* 1)Enviar imagem para o seu local (S3 por exemplo),
         * 2)Pegar os links dos locais para onde enviamos,
         * 3)Associar esses links ao produto
         */

        Set<String> links = uploaderFake.envia(imagemRequest.getImagens());

        //Metodo vincula o produto com suas imagens,
        //Como nesse método criarei a imagem, já faço o vinculo de imagem com produto tambem.
        produto.adicionaImagem(links);

        return ResponseEntity.ok(new ProdutoResponse(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalheResponse> detalhe(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).get();
        List<Opiniao> opinioes = opiniaoRepository.findByProdutoId(id);
        List<Pergunta> perguntas = perguntaRepository.findByProdutoId(id);
        ProdutoDetalheResponse produtoDetalheResponse = new ProdutoDetalheResponse(produto, opinioes, perguntas);
        return ResponseEntity.ok(produtoDetalheResponse);
    }
}
