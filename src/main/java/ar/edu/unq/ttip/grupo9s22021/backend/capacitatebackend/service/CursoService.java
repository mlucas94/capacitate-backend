package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.service;

import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.Curso;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.CapacitateException;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.CursoNoExisteException;
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

    public Curso findById(Integer id) throws CapacitateException {
        Optional<Curso> curso = repository.findById(id);
        if(curso.isPresent()) {
            return curso.get();
        } else {
            throw new CursoNoExisteException();
        }
    }

    public List<Curso> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Curso agregarReserva(Integer id, String dni) throws CapacitateException {
        Optional<Curso> resultado = repository.findById(id);
        if(resultado.isPresent()) {
            Curso curso = resultado.get();
            curso.reservarCupo(dni);
            repository.save(curso);
            return curso;
        }
        return null;
    }

    public Boolean usuarioEstaInscripto(Integer id, String dni) throws CapacitateException {
        Optional<Curso> resultado = repository.findById(id);
        if(resultado.isPresent()) {
            Curso curso = resultado.get();
            return curso.estaInscripto(dni);
        }
        return null;
    }

    @Transactional
    public Curso quitarReserva(Integer id, String dni) throws CapacitateException {
        Optional<Curso> resultado = repository.findById(id);
        if(resultado.isPresent()) {
            Curso curso = resultado.get();
            curso.cancelarReserva(dni);
            repository.save(curso);
            return curso;
        }
        return null;
    }
}
