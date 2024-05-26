package br.com.fiap.api.ais_ecommerce.dominio.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
        Long id,

        @NotBlank(message = "O nome não pode estar em branco.")
        String nome

) {
}
