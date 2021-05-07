package br.com.zupacademy.rodrigo.ecommerce.caracteristica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {
}
