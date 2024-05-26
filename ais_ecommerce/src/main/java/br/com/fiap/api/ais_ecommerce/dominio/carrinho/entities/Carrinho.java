package br.com.fiap.api.ais_ecommerce.dominio.carrinho.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToMany produto
    //@OneToOne cliente

    private Long quantidadeProduto;

    public Carrinho() {}

    public Carrinho(Long id, Long quantidadeProduto) {
        this.id = id;
        this.quantidadeProduto = quantidadeProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Long quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return Objects.equals(id, carrinho.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", quantidadeProduto=" + quantidadeProduto +
                '}';
    }
}

