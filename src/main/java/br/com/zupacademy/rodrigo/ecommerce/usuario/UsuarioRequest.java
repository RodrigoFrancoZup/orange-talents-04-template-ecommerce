package br.com.zupacademy.rodrigo.ecommerce.usuario;


import br.com.zupacademy.rodrigo.ecommerce.annotation.UniqueValue;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converteUsuarioRequestParaUsuario() {
        return new Usuario(login, senha);
    }
}
