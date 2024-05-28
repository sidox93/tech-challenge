package br.com.fiap.api.ais_ecommerce.dominio.carrinho.controller;

import br.com.fiap.api.ais_ecommerce.dominio.carrinho.dto.CarrinhoDTO;
import br.com.fiap.api.ais_ecommerce.dominio.carrinho.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<Page<CarrinhoDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
        Page<CarrinhoDTO> carrinhosDTO = carrinhoService.findAll(pageable);
        return ResponseEntity.ok(carrinhosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoDTO> findById(@PathVariable Long id) {
        CarrinhoDTO carrinhoDTO = carrinhoService.findById(id);
        return ResponseEntity.ok(carrinhoDTO);
    }

    @PostMapping
    public ResponseEntity<CarrinhoDTO> save(@Valid @RequestBody CarrinhoDTO carrinhoDTO) {
        CarrinhoDTO savedCarrinho = carrinhoService.save(carrinhoDTO);
        return new ResponseEntity<>(savedCarrinho, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoDTO> update(@PathVariable Long id, @RequestBody CarrinhoDTO carrinhoDTO) {
        CarrinhoDTO updatedCarrinho = carrinhoService.update(id, carrinhoDTO);
        return ResponseEntity.ok(updatedCarrinho);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carrinhoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

