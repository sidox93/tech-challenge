package br.com.fiap.api.ais_ecommerce.dominio.categoria.dto;

import br.com.fiap.api.ais_ecommerce.dominio.produto.entities.Produto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoriaDTO(
        Long id,

        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,

        @NotBlank(message = "A descricao não pode estar em branco.")
        String descricao,

        List<Produto> produtos


) {
}
