package br.com.fiap.api.ais_ecommerce.dominio.carrinho.service;

import br.com.fiap.api.ais_ecommerce.dominio.carrinho.dto.CarrinhoDTO;
import br.com.fiap.api.ais_ecommerce.dominio.carrinho.entities.Carrinho;
import br.com.fiap.api.ais_ecommerce.dominio.carrinho.repository.ICarrinhoRepository;
import br.com.fiap.api.ais_ecommerce.exception.ControllerNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    @Autowired
    private ICarrinhoRepository iCarrinhoRepository;

    @Autowired
    public CarrinhoService(ICarrinhoRepository iCarrinhoRepository) {
        this.iCarrinhoRepository = iCarrinhoRepository;
    }

    public Page<CarrinhoDTO> findAll(Pageable pageable) {
        Page<Carrinho> carrinhos = iCarrinhoRepository.findAll(pageable);
        return carrinhos.map(this::toCarrinhoDTO);
    }

    public CarrinhoDTO findById(Long id) {
        Carrinho carrinho = iCarrinhoRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFundException("Carrinho não encontrado"));
        return toCarrinhoDTO(carrinho);
    }

    public CarrinhoDTO save(CarrinhoDTO produtoDTO) {
        Carrinho carrinho = toCarrinho(produtoDTO);
        carrinho = iCarrinhoRepository.save(carrinho);
        return toCarrinhoDTO(carrinho);
    }

    public CarrinhoDTO update(Long id, CarrinhoDTO produtoDTO) {
        try {
            Carrinho carrinho = iCarrinhoRepository.getReferenceById(id);

            carrinho.setQuantidadeProduto(produtoDTO.quantidadeProduto());

            carrinho = iCarrinhoRepository.save(carrinho);

            return toCarrinhoDTO(carrinho);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFundException("Carrinho não encontrado");
        }
    }

    public void delete(Long id) {
        iCarrinhoRepository.deleteById(id);
    }

    private CarrinhoDTO toCarrinhoDTO(Carrinho carrinho) {
        return new CarrinhoDTO(
                carrinho.getId(),
                carrinho.getQuantidadeProduto(),
                carrinho.getProdutos()
        );
    }

    private Carrinho toCarrinho(CarrinhoDTO carrinhoDTO) {
        return new Carrinho(
               carrinhoDTO.id(),
               carrinhoDTO.quantidadeProduto(),
                carrinhoDTO.produtos()
        );
    }
}
