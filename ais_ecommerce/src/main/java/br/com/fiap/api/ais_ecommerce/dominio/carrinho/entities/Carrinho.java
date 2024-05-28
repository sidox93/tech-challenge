package br.com.fiap.api.ais_ecommerce.dominio.carrinho.entities;

import br.com.fiap.api.ais_ecommerce.dominio.produto.entities.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_CARRINHO")
public class Carrinho {

    @Id
    @Column(name = "ID_CARRINHO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "QT_QUANTIDADE_PRODUTO",length = 30, nullable = false)
    private Long quantidadeProduto;

    @JsonIgnore
    @OneToMany(mappedBy="carrinho")
    private List<Produto> produtos = new ArrayList<>();

    public Carrinho() {}

    public Carrinho(Long id, Long quantidadeProduto, List<Produto> produtos) {
        this.id = id;
        this.quantidadeProduto = quantidadeProduto;
        this.produtos = produtos;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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
                ", produtos=" + produtos +
                '}';
    }
}

