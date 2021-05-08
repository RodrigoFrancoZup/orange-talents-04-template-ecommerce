package br.com.zupacademy.rodrigo.ecommerce.usuario;

import br.com.zupacademy.rodrigo.ecommerce.perfil.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Perfil> perfis = new ArrayList<>();

    @NotNull
    @PastOrPresent
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.criptografaSenha();
    }

    @Deprecated
    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() { return senha;
    }

    public void criptografaSenha(){
        this.senha = new BCryptPasswordEncoder().encode(this.senha);
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return login != null ? login.equals(usuario.login) : usuario.login == null;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }
}
