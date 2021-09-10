package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.persistence;

import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.Curso;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface CursoPersistence extends CrudRepository<Curso, Integer> {

    Optional<Curso> findById(Integer id);

    List<Curso> findAll();

}
