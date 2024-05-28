package br.com.fiap.api.ais_ecommerce.dominio.cliente.repository;


import br.com.fiap.api.ais_ecommerce.dominio.cliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
