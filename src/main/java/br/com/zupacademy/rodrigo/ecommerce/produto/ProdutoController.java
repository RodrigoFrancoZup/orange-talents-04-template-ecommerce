package br.com.zupacademy.rodrigo.ecommerce.produto;

import br.com.zupacademy.rodrigo.ecommerce.caracteristica.Caracteristica;
import br.com.zupacademy.rodrigo.ecommerce.caracteristica.CaracteristicaRepository;
import br.com.zupacademy.rodrigo.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import br.com.zupacademy.rodrigo.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<ProdutoResponse> cadastra(@RequestBody @Valid ProdutoRequest produtoRequest) {
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Usuario usuario = usuarioRepository.findByLogin(login).get();

        Produto produto = produtoRequest.converteProdutoRequestParaProduto(categoriaRepository);
        produto.setUsuario(usuario);
        for(Caracteristica caracteristica : produto.getCaracteristicas()){
            caracteristica.setProduto(produto);
        }

        Produto produtoSalvo = produtoRepository.save(produto);

        /*
        for(Caracteristica aux : produto.getCaracteristicas()){
            aux.setProduto(produtoSalvo);
            caracteristicaRepository.save(aux);
        }
        */
        return ResponseEntity.ok(new ProdutoResponse(produto));

    }
}
