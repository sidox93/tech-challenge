package br.com.fiap.api.ais_ecommerce.dominio.carrinho.repository;

import br.com.fiap.api.ais_ecommerce.dominio.carrinho.entities.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
