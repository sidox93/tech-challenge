package br.com.fiap.api.ais_ecommerce.dominio.endereco.repoditory;

import br.com.fiap.api.ais_ecommerce.dominio.endereco.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
}
