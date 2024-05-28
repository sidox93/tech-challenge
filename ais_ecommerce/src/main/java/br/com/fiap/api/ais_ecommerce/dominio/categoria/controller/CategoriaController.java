package br.com.fiap.api.ais_ecommerce.dominio.categoria.controller;

import br.com.fiap.api.ais_ecommerce.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.api.ais_ecommerce.dominio.categoria.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable) {
        Page<CategoriaDTO> CategoriasDTO = categoriaService.findAll(pageable);
        return ResponseEntity.ok(CategoriasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        CategoriaDTO categoriaDTO = categoriaService.findById(id);
        return ResponseEntity.ok(categoriaDTO);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO savedCategoria = categoriaService.save(categoriaDTO);
        return new ResponseEntity<>(savedCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO updatedCategoria = categoriaService.update(id, categoriaDTO);
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

