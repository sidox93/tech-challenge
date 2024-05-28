package br.com.fiap.api.ais_ecommerce.dominio.endereco.dto;

import br.com.fiap.api.ais_ecommerce.dominio.cliente.entities.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(

        Long id,

        @NotBlank(message = "O nome da rua não pode estar em branco")
        String rua,

        @NotBlank(message = "O nome da cidade não pode estar em branco")
        String cidade,

        @Size(min = 2, max = 2 , message = "O estado deve ter exatamente 2 caracteres")
        @NotBlank(message = "O nome da estado não pode estar em branco")
        String estado,

        @NotBlank(message = "O CEP não pode estar em branco")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ertar no formato 00000-000")
        String cep,

        Cliente cliente
) {
}
