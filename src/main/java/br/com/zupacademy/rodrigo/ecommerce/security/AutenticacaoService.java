package br.com.zupacademy.rodrigo.ecommerce.security;

import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import br.com.zupacademy.rodrigo.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        if(usuario.isPresent()){
            return usuario.get();
        }
       throw new UsernameNotFoundException("Dados inválidos!");
    }
}
