package br.com.fiap.api.ais_ecommerce.dominio.categoria.repository;


import br.com.fiap.api.ais_ecommerce.dominio.categoria.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
