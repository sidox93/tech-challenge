package br.com.fiap.api.ais_ecommerce.dominio.produto.repository;

import br.com.fiap.api.ais_ecommerce.dominio.produto.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, UUID> {
}
