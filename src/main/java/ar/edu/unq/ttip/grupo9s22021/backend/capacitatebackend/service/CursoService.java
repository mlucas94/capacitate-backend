package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.service;

import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.Curso;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.persistence.CursoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    public CursoPersistence repository;

    public Curso findById(Integer id) {
        Optional<Curso> curso = repository.findById(id);
        if(curso.isPresent()) {
            return curso.get();
        }
        return null;
    }

    public List<Curso> findAll() {
        return repository.findAll();
    }

    public Curso agregarReserva(Integer id) throws Exception {
        Optional<Curso> resultado = repository.findById(id);
        if(resultado.isPresent()) {
            Curso curso = resultado.get();
            curso.reservarCupo("1234");
            repository.save(curso);
            return curso;
        }
        return null;
    }
}
