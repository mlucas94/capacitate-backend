package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.persistence;

import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Configuration
@Repository
public interface UsuarioPersistence extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findByNombreDeUsuario(String nombreDeUsuario);
}
