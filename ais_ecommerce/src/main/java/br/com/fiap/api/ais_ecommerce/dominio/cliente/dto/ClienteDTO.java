package br.com.fiap.api.ais_ecommerce.dominio.cliente.dto;

import br.com.fiap.api.ais_ecommerce.dominio.carrinho.entities.Carrinho;
import br.com.fiap.api.ais_ecommerce.dominio.endereco.entities.Endereco;
import br.com.fiap.api.ais_ecommerce.dominio.usuario.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

public record ClienteDTO(
        Long id,

        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,

        @Email(message = "O E-mail é inválido.")
        @NotBlank(message = "O E-mail não pode estar em branco.")
        String email,

        @CPF(message = "O CPF é inválido.")
        String cpf,

        @NotNull(message = "A data de nascimento não pode estar em branco.")
        LocalDate dataNascimento,

        @NotBlank(message = "O telefone não pode estar em branco.")
        String telefone,

        List<Endereco> enderecos,

        Carrinho carrinho,

        Usuario usuario

) {
}