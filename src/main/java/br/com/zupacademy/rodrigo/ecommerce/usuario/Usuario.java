package br.com.zupacademy.rodrigo.ecommerce.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

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
}
