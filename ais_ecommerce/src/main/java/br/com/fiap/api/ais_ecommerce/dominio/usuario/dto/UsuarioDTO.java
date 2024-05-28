package br.com.fiap.api.ais_ecommerce.dominio.usuario.dto;

import br.com.fiap.api.ais_ecommerce.dominio.cliente.entities.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(

        Long id,

        @NotBlank(message = "O nome de usuário não pode estar em branco")
        String username,

        @NotBlank(message = "O password não pode estar em branco")
        @Size(min = 6, max = 15, message = "A senha deve ter pelo menos {min} caracteres e no maximo {max}")
        String password,

        Cliente cliente

) {
}
