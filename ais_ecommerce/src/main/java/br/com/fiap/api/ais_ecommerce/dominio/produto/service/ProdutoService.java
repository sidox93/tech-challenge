package br.com.fiap.api.ais_ecommerce.dominio.produto.service;

import br.com.fiap.api.ais_ecommerce.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.api.ais_ecommerce.dominio.produto.entities.Produto;
import br.com.fiap.api.ais_ecommerce.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.api.ais_ecommerce.exception.ControllerNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository iProdutoRepository;

    @Autowired
    public ProdutoService(IProdutoRepository iProdutoRepository) {
        this.iProdutoRepository = iProdutoRepository;
    }

    public Page<ProdutoDTO> findAll(Pageable pageable) {
        Page<Produto> produtos = iProdutoRepository.findAll(pageable);
        return produtos.map(this::toProdutoDTO);
    }

    public ProdutoDTO findById(UUID id) {
        Produto produto = iProdutoRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFundException("Produto não encontrado"));
        return toProdutoDTO(produto);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Produto produto = toProduto(produtoDTO);
        produto = iProdutoRepository.save(produto);
        return toProdutoDTO(produto);
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produtoDTO) {
        try {
            Produto produto = iProdutoRepository.getReferenceById(id);

            produto.setNome(produtoDTO.nome());
            produto.setDescricao(produtoDTO.descricao());
            produto.setQuantidade(produtoDTO.quantidade());
            produto.setPreco(produtoDTO.preco());
            produto.setUrlImage(produtoDTO.urlImage());

            produto = iProdutoRepository.save(produto);

            return toProdutoDTO(produto);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFundException("Produto não encontrado");
        }
    }

    public void delete(UUID id) {
        iProdutoRepository.deleteById(id);
    }

    private ProdutoDTO toProdutoDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getPreco(),
                produto.getUrlImage(),
                produto.getCategoria(),
                produto.getCarrinho()
        );
    }

    private Produto toProduto(ProdutoDTO produtoDTO) {
        return new Produto(
                produtoDTO.id(),
                produtoDTO.nome(),
                produtoDTO.descricao(),
                produtoDTO.quantidade(),
                produtoDTO.preco(),
                produtoDTO.urlImage(),
                produtoDTO.categoria(),
                produtoDTO.carrinho()
        );
    }
}
