package br.com.fiap.api.ais_ecommerce.dominio.endereco.service;

import br.com.fiap.api.ais_ecommerce.dominio.endereco.dto.EnderecoDTO;
import br.com.fiap.api.ais_ecommerce.dominio.endereco.entities.Endereco;
import br.com.fiap.api.ais_ecommerce.dominio.endereco.repoditory.IEnderecoRepository;
import br.com.fiap.api.ais_ecommerce.exception.ControllerNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final IEnderecoRepository iEnderecoRepository;

    public EnderecoService(IEnderecoRepository iEnderecoRepository) {
        this.iEnderecoRepository = iEnderecoRepository;
    }

    public Page<EnderecoDTO> findAll(Pageable pageable) {
        Page<Endereco> enderecos = iEnderecoRepository.findAll(pageable);
        return enderecos.map(this::toEnderecoDTO);
    }

    public EnderecoDTO findById(Long id) {
        Endereco endereco = iEnderecoRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFundException("Endereço não encontrado"));
        return toEnderecoDTO(endereco);
    }

    public EnderecoDTO save(EnderecoDTO enderecoDTO) {
        Endereco endereco = toEndereco(enderecoDTO);
        endereco = iEnderecoRepository.save(endereco);
        return toEnderecoDTO(endereco);
    }

    public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO) {
        try {
            Endereco endereco = iEnderecoRepository.getReferenceById(id);

            endereco.setRua(enderecoDTO.rua());
            endereco.setCidade(enderecoDTO.cidade());
            endereco.setEstado(enderecoDTO.estado());
            endereco.setCep(enderecoDTO.cep());

            endereco = iEnderecoRepository.save(endereco);

            return toEnderecoDTO(endereco);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFundException("Endereço  não encontrado");
        }
    }

    public void delete(Long id) {
        iEnderecoRepository.deleteById(id);
    }

    private Endereco toEndereco(EnderecoDTO enderecoDTO) {
        return new Endereco(
                enderecoDTO.id(),
                enderecoDTO.rua(),
                enderecoDTO.cidade(),
                enderecoDTO.estado(),
                enderecoDTO.cep()
        );
    }

    private EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }
}
