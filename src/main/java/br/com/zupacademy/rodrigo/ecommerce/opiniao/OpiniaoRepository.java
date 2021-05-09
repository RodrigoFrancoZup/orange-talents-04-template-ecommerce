package br.com.zupacademy.rodrigo.ecommerce.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {

    public List<Opiniao> findByProdutoId(Long id);
}
