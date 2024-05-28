package br.com.fiap.api.ais_ecommerce.dominio.categoria.service;

import br.com.fiap.api.ais_ecommerce.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.api.ais_ecommerce.dominio.categoria.entities.Categoria;
import br.com.fiap.api.ais_ecommerce.dominio.categoria.repository.ICategoriaRepository;
import br.com.fiap.api.ais_ecommerce.exception.ControllerNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository iCategoriaRepository;

    @Autowired
    public CategoriaService(ICategoriaRepository iCategoriaRepository) {
        this.iCategoriaRepository = iCategoriaRepository;
    }

    public Page<CategoriaDTO> findAll(Pageable pageable) {
        Page<Categoria> categorias = iCategoriaRepository.findAll(pageable);
        return categorias.map(this::toCategoriaDTO);
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = iCategoriaRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFundException("Categoria não encontrada"));
        return toCategoriaDTO(categoria);
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = toCategoria(categoriaDTO);
        categoria = iCategoriaRepository.save(categoria);
        return toCategoriaDTO(categoria);
    }

    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {
        try {
            Categoria categoria = iCategoriaRepository.getReferenceById(id);

           categoria.setNome(categoriaDTO.nome());
           categoria.setDescricao(categoriaDTO.descricao());

            categoria = iCategoriaRepository.save(categoria);

            return toCategoriaDTO(categoria);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFundException("Categoria não encontrado");
        }
    }

    public void delete(Long id) {
        iCategoriaRepository.deleteById(id);
    }

    private CategoriaDTO toCategoriaDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getProdutos()
        );
    }

    private Categoria toCategoria(CategoriaDTO categoriaDTO) {
        return new Categoria(
                categoriaDTO.id(),
                categoriaDTO.nome(),
                categoriaDTO.descricao(),
                categoriaDTO.produtos()
        );
    }
}

