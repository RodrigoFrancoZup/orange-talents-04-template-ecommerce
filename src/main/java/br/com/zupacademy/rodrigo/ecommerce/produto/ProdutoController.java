package br.com.zupacademy.rodrigo.ecommerce.produto;


import br.com.zupacademy.rodrigo.ecommerce.caracteristica.CaracteristicaRepository;
import br.com.zupacademy.rodrigo.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import br.com.zupacademy.rodrigo.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import javax.validation.Valid;


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


    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoResponse> cadastra(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario) {

        Produto produto = produtoRequest.converteProdutoRequestParaProduto(categoriaRepository);
        produto.setUsuario(usuario);

        produtoRepository.save(produto);

        return ResponseEntity.ok(new ProdutoResponse(produto));

    }
}
