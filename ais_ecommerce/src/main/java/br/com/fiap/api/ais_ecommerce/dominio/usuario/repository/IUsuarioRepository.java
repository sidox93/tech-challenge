package br.com.fiap.api.ais_ecommerce.dominio.usuario.repository;

import br.com.fiap.api.ais_ecommerce.dominio.usuario.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
