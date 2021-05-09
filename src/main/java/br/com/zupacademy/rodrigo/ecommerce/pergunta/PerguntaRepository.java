package br.com.zupacademy.rodrigo.ecommerce.pergunta;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    public List<Pergunta> findByProdutoId(Long id);
}
