package br.com.fiap.api.ais_ecommerce.dominio.categoria.entities;

import br.com.fiap.api.ais_ecommerce.dominio.produto.entities.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria {

    @Id
    @Column(name = "ID_CATEGORIA", nullable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "NM_NOME",length = 50, nullable = false)
    private String nome;

    @NotEmpty
    @Column(name = "DS_DESCRICAO",length = 150, nullable = false)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos = new ArrayList<>();

    public Categoria() {}

    public Categoria(Long id, String nome, String descricao, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}
