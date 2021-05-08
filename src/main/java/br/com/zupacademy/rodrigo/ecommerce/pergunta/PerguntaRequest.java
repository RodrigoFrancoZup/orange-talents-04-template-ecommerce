package br.com.zupacademy.rodrigo.ecommerce.pergunta;


import br.com.zupacademy.rodrigo.ecommerce.produto.Produto;
import br.com.zupacademy.rodrigo.ecommerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;


public class PerguntaRequest {

    @NotBlank
    @Length(min = 5)
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String conteudo;

    public PerguntaRequest(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public Pergunta convertePerguntRequestParaPergunta(Usuario usuario, Produto produto){
        return new Pergunta(this.titulo, this.conteudo, usuario, produto);
    }
}
