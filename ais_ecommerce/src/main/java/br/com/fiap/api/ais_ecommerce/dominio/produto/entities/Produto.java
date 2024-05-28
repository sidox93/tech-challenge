package br.com.fiap.api.ais_ecommerce.dominio.produto.entities;

import br.com.fiap.api.ais_ecommerce.dominio.carrinho.entities.Carrinho;
import br.com.fiap.api.ais_ecommerce.dominio.categoria.entities.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @Column(name = "ID_PRODUTO", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty
    @Column(name = "NM_NOME",length = 50, nullable = false)
    private String nome;

    @NotEmpty
    @Column(name = "DS_DESCRICAO",length = 150, nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "QT_QUANTIDADE",length = 30, nullable = false)
    private Integer quantidade;

    @NotNull
    @Column(name = "PR_PRECO",length = 30, nullable = false)
    private double preco;

    @Column(name = "IM_IMAGE",length = 30, nullable = false)
    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA", nullable=false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ID_CARRINHO", referencedColumnName = "ID_CARRINHO", nullable=false)
    private Carrinho carrinho;

    public Produto() {
    }

    public Produto(UUID id, String nome, String descricao, Integer quantidade, double preco, String urlImage, Categoria categoria, Carrinho carrinho) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.urlImage = urlImage;
        this.categoria = categoria;
        this.carrinho = carrinho;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                ", urlImage='" + urlImage + '\'' +
                ", categoria=" + categoria +
                ", carrinho=" + carrinho +
                '}';
    }
}