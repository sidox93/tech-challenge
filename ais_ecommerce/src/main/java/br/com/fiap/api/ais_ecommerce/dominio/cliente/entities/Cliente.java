package br.com.fiap.api.ais_ecommerce.dominio.cliente.entities;

import br.com.fiap.api.ais_ecommerce.dominio.carrinho.entities.Carrinho;
import br.com.fiap.api.ais_ecommerce.dominio.endereco.entities.Endereco;
import br.com.fiap.api.ais_ecommerce.dominio.usuario.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="TB_CLIENTE")
public class Cliente {

    @Id
    @Column(name = "ID_CLIENTE", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NM_NOME",length = 60, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "NM_EMAIL",length = 60, nullable = false)
    private String email;

    @NotNull
    @Column(name = "NR_CPF",length = 11, nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "DT_NASCIMENTO",length = 40, nullable = false)
    private LocalDate dataNascimento;

    @NotNull
    @Column(name = "NR_TELEFONE",length = 60, nullable = false)
    private String telefone;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ID_CARRINHO", referencedColumnName = "ID_CARRINHO", nullable=false)
    private Carrinho carrinho;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable=false)
    private Usuario usuario;

    public Cliente() {}

    public Cliente(Long id, String nome, String email, String cpf, LocalDate dataNascimento, String telefone, List<Endereco> enderecos, Carrinho carrinho, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.enderecos = enderecos;
        this.carrinho = carrinho;
        this.usuario = usuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", telefone='" + telefone + '\'' +
                ", enderecos=" + enderecos +
                ", carrinho=" + carrinho +
                ", usuario=" + usuario +
                '}';
    }
}
