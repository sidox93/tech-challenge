package br.com.fiap.api.ais_ecommerce.dominio.usuario.service;

import br.com.fiap.api.ais_ecommerce.dominio.usuario.dto.UsuarioDTO;
import br.com.fiap.api.ais_ecommerce.dominio.usuario.entities.Usuario;
import br.com.fiap.api.ais_ecommerce.dominio.usuario.repository.IUsuarioRepository;
import br.com.fiap.api.ais_ecommerce.exception.ControllerNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private UsuarioService(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> usuarios = iUsuarioRepository.findAll(pageable);
        return usuarios.map(this::toUsuarioDTO);
    }

    public UsuarioDTO findById(Long id) {
        Usuario usuario = iUsuarioRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFundException("Usuario não encontrado"));
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = toUsuario(usuarioDTO);
        usuario = iUsuarioRepository.save(usuario);
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = iUsuarioRepository.getReferenceById(id);

            usuario.setUsername(usuarioDTO.username());
            usuario.setPassword(usuarioDTO.password());

            usuario = iUsuarioRepository.save(usuario);

            return toUsuarioDTO(usuario);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFundException("Usuario não encontrado");
        }
    }

    public void delete(Long id) {
        iUsuarioRepository.deleteById(id);
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword()
        );
    }

    private Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return new Usuario(
                usuarioDTO.id(),
                usuarioDTO.username(),
                usuarioDTO.password()
        );
    }
}
