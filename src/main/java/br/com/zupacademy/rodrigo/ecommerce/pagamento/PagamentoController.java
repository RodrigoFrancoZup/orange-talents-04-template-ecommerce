package br.com.zupacademy.rodrigo.ecommerce.pagamento;

import br.com.zupacademy.rodrigo.ecommerce.compra.Compra;
import br.com.zupacademy.rodrigo.ecommerce.compra.CompraRepository;
import br.com.zupacademy.rodrigo.ecommerce.compra.StatusCompra;
import br.com.zupacademy.rodrigo.ecommerce.utils.EnvioDeEmail;
import br.com.zupacademy.rodrigo.ecommerce.utils.GeradoraDeNotaFiscal;
import br.com.zupacademy.rodrigo.ecommerce.utils.RankingDeVendedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping("/retorno-paypal/{idCompra}")
    @Transactional
    public ResponseEntity<?> pagamentoPaypal(@RequestBody @Valid PagamentoResponsePaypal pagamentoResponsePaypal, @PathVariable Long idCompra){
        Compra compra = compraRepository.findById(idCompra).get();
        if(pagamentoResponsePaypal.getStatus().equals("1")){

            Pagamento pagamento = new Pagamento(compra, StatusPagamento.SUCESSO);

            compra.setStatus(StatusCompra.GERANDO_NF);
            GeradoraDeNotaFiscal gnf = new GeradoraDeNotaFiscal();
            gnf.geraNF(compra, pagamento);

            RankingDeVendedores rank = new RankingDeVendedores();
            rank.avaliaVendedor(compra);

            pagamentoRepository.save(pagamento);
            return ResponseEntity.ok(new PagamentoResponse(pagamento));
        }else {
            Pagamento pagamento = new Pagamento(compra, StatusPagamento.ERRO);
            EnvioDeEmail envioDeEmail = new EnvioDeEmail();
            envioDeEmail.envioEmailComprador(pagamento);
            pagamentoRepository.save(pagamento);
            return ResponseEntity.badRequest().body(new PagamentoResponse(pagamento));
        }
    }

    @PostMapping("/retorno-pagseguro/{idCompra}")
    public ResponseEntity<?> pagamentoPagseguro(@RequestBody @Valid PagamentoResponsePagseguro pagamentoResponsePagseguro,  @PathVariable Long idCompra){
        Compra compra = compraRepository.findById(idCompra).get();
        if(pagamentoResponsePagseguro.getStatus().equals("SUCESSO")){

            Pagamento pagamento = new Pagamento(compra, StatusPagamento.SUCESSO);

            compra.setStatus(StatusCompra.GERANDO_NF);
            GeradoraDeNotaFiscal gnf = new GeradoraDeNotaFiscal();
            gnf.geraNF(compra, pagamento);

            RankingDeVendedores rank = new RankingDeVendedores();
            rank.avaliaVendedor(compra);

            pagamentoRepository.save(pagamento);
            return ResponseEntity.ok(new PagamentoResponse(pagamento));
        }else {
            Pagamento pagamento = new Pagamento(compra, StatusPagamento.ERRO);
            EnvioDeEmail envioDeEmail = new EnvioDeEmail();
            envioDeEmail.envioEmailComprador(pagamento);
            pagamentoRepository.save(pagamento);
            return ResponseEntity.badRequest().body(new PagamentoResponse(pagamento));
        }
    }
}
