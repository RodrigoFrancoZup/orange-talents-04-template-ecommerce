package br.com.zupacademy.rodrigo.ecommerce.produto;

import br.com.zupacademy.rodrigo.ecommerce.caracteristica.CaracteristicaResponse;
import br.com.zupacademy.rodrigo.ecommerce.imagem.Imagem;
import br.com.zupacademy.rodrigo.ecommerce.opiniao.Opiniao;
import br.com.zupacademy.rodrigo.ecommerce.opiniao.OpiniaoResponse;
import br.com.zupacademy.rodrigo.ecommerce.pergunta.Pergunta;
import br.com.zupacademy.rodrigo.ecommerce.pergunta.PerguntaResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDetalheResponse {

    private List<String> linksImagens = new ArrayList<>();
    private String produto;
    private BigDecimal preco;
    private List<CaracteristicaResponse> caracteristicas;
    private String descricao;
    private Double mediaDasNotas = 0.0;
    private Integer notaTotal = 0;
    private List<OpiniaoResponse> opinioesDoProduto = new ArrayList<>();
    private List<PerguntaResponse> perguntasDoProduto = new ArrayList<>();

    public ProdutoDetalheResponse(Produto produto, List<Opiniao> opinioes, List<Pergunta> perguntas) {

        this.produto = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = CaracteristicaResponse.converteListaDeCaracteristicaParaCaracteristicaResponse(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.mediaDasNotas = calculaMedia(opinioes);
        this.notaTotal = notaTotal(opinioes);
        for (Opiniao opiniao : opinioes) {
            this.opinioesDoProduto.add(new OpiniaoResponse(opiniao));
        }
        for (Pergunta pergunta : perguntas) {
            this.perguntasDoProduto.add(new PerguntaResponse(pergunta));
        }
        for (Imagem imagem : produto.getImagens()) {
            this.linksImagens.add(imagem.getLink());
        }

    }

    private Integer notaTotal(List<Opiniao> opinioes) {
        Integer notaTotal = 0;
        for (Opiniao opiniao : opinioes) {
            notaTotal += opiniao.getNota();
        }
        return notaTotal;
    }

    public Double calculaMedia(List<Opiniao> opinioes) {
        Double media = 0.0;
        for (Opiniao opiniao : opinioes) {
            media += opiniao.getNota();
        }
        return media /= opinioes.size();
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }

    public String getProduto() {
        return produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaDasNotas() {
        return mediaDasNotas;
    }

    public Integer getNotaTotal() {
        return notaTotal;
    }

    public List<OpiniaoResponse> getOpinioesDoProduto() {
        return opinioesDoProduto;
    }

    public List<PerguntaResponse> getPerguntasDoProduto() {
        return perguntasDoProduto;
    }
}
