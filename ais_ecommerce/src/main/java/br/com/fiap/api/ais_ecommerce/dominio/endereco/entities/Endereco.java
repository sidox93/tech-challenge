package br.com.fiap.api.ais_ecommerce.dominio.endereco.entities;

import br.com.fiap.api.ais_ecommerce.dominio.cliente.entities.Cliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Table(name="TB_ENDERECO")
@Entity
public class Endereco {

    @Id
    @Column(name = "ID_ENDERECO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NM_RUA",length = 60, nullable = false)
    private String rua;

    @NotNull
    @Column(name = "NM_CIDADE",length = 60, nullable = false)
    private String cidade;

    @NotNull
    @Column(name = "NM_ESTADO",length = 30, nullable = false)
    private String estado;

    @NotNull
    @Column(name = "NR_CEP",length = 10, nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", nullable=false)
    private Cliente cliente;

    public Endereco() {}

    public Endereco(Long id, String rua, String cidade, String estado, String cep, Cliente cliente) {
        this.id = id;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}