package br.com.fiap.api.ais_ecommerce.dominio.carrinho.dto;

import br.com.fiap.api.ais_ecommerce.dominio.produto.entities.Produto;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CarrinhoDTO(
        Long id,

        @Positive(message = "A quantidade do produto tem que ser positiva ")
        Long quantidadeProduto,

        List<Produto> produtos

) {
}
