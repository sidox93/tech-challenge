package br.com.fiap.api.ais_ecommerce.dominio.cliente.service;


import br.com.fiap.api.ais_ecommerce.dominio.cliente.dto.ClienteDTO;
import br.com.fiap.api.ais_ecommerce.dominio.cliente.entities.Cliente;
import br.com.fiap.api.ais_ecommerce.dominio.cliente.repository.IClienteRepository;
import br.com.fiap.api.ais_ecommerce.exception.ControllerNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;

    @Autowired
    public ClienteService(IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }

    public Page<ClienteDTO> findAll(Pageable pageable) {
        Page<Cliente> categorias = iClienteRepository.findAll(pageable);
        return categorias.map(this::toClienteDTO);
    }

    public ClienteDTO findById(Long id) {
        Cliente cliente = iClienteRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFundException("Cliente não encontrada"));
        return toClienteDTO(cliente);
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = toCliente(clienteDTO);
        cliente = iClienteRepository.save(cliente);
        return toClienteDTO(cliente);
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        try {
            Cliente cliente = iClienteRepository.getReferenceById(id);

            cliente.setNome(clienteDTO.nome());
            cliente.setEmail(clienteDTO.email());
            cliente.setCpf(clienteDTO.cpf());
            cliente.setDataNascimento(clienteDTO.dataNascimento());
            cliente.setTelefone(clienteDTO.telefone());

            cliente = iClienteRepository.save(cliente);

            return toClienteDTO(cliente);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFundException("Cliente não encontrado");
        }
    }

    public void delete(Long id) {
        iClienteRepository.deleteById(id);
    }

    private ClienteDTO toClienteDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getDataNascimento(),
                cliente.getTelefone()
        );
    }

    private Cliente toCliente(ClienteDTO clienteDTO) {
        return new Cliente(
                clienteDTO.id(),
                clienteDTO.nome(),
                clienteDTO.email(),
                clienteDTO.cpf(),
                clienteDTO.dataNascimento(),
                clienteDTO.telefone()
        );
    }
}
