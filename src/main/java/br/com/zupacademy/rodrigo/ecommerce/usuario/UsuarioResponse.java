package br.com.zupacademy.rodrigo.ecommerce.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioResponse {

    private Long id;
    private String login;
    private LocalDateTime instanteCriacao;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.instanteCriacao = usuario.getInstanteCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }
}
