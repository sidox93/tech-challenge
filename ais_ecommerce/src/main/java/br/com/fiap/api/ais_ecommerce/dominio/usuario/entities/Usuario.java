package br.com.fiap.api.ais_ecommerce.dominio.usuario.entities;

import br.com.fiap.api.ais_ecommerce.dominio.cliente.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "US_USER",length = 60, nullable = false)
    private String username;

    @NotNull
    @Column(name = "PW_PASSWORD",length = 60, nullable = false)
    private String password;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Cliente cliente;

    public Usuario() {}

    public Usuario(Long id, String username, String password, Cliente cliente) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
