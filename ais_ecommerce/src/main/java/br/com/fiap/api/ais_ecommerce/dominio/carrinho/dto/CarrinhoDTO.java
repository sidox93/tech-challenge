package br.com.fiap.api.ais_ecommerce.dominio.carrinho.dto;

import jakarta.validation.constraints.Positive;

public record CarrinhoDTO(
        Long id,

        @Positive(message = "A quantidade do produto tem que ser positiva ")
        Long quantidadeProduto
) {
}
