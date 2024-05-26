package br.com.fiap.api.ais_ecommerce.dominio.endereco.controller;

import br.com.fiap.api.ais_ecommerce.dominio.endereco.service.EnderecoService;
import br.com.fiap.api.ais_ecommerce.dominio.endereco.dto.EnderecoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService){
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = "cep") Pageable pageable){
        Page<EnderecoDTO> clientesDTO = enderecoService.findAll(pageable);
        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id){
        EnderecoDTO clienteDTO = enderecoService.findById(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> save(@Valid @RequestBody EnderecoDTO clienteDTO){
        EnderecoDTO savedCliente = enderecoService.save(clienteDTO);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody EnderecoDTO clienteDTO){
        EnderecoDTO updateCliente = enderecoService.update(id, clienteDTO);
        return ResponseEntity.ok(updateCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
