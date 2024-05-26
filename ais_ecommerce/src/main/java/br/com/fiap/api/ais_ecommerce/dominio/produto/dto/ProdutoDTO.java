package br.com.fiap.api.ais_ecommerce.dominio.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProdutoDTO(

        UUID id,

        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,

        String descricao,

        @Positive(message = "A quantidade do produto tem que ser positivo")
        Integer quantidade,

        @Positive(message = "O valor do produto tem que ser positivo")
        double preco,

        String urlImage
) {
}
