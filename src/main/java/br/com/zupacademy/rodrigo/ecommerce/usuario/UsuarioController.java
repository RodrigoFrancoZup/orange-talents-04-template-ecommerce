package br.com.zupacademy.rodrigo.ecommerce.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponse> cadastra(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRequest.converteUsuarioRequestParaUsuario();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new UsuarioResponse(usuario));
    }
}
