package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.webservice;

import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.Curso;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.Usuario;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.CapacitateException;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.CursoLlenoException;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableAutoConfiguration
public class CursoWebService {

    @Autowired
    public CursoService service;

    @GetMapping("/api/curso/{id}")
    public ResponseEntity<Curso> byCursoId(@PathVariable Integer id) throws CapacitateException {
        Curso foundCurso = service.findById(id);
        if(foundCurso == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(foundCurso);
        }
    }

    @GetMapping("/api/curso")
    public ResponseEntity<List<Curso>> allCursos() {
        List<Curso> foundCursos = service.findAll();
        return ResponseEntity.ok(foundCursos);
    }

    @PostMapping("/api/curso/{id}")
    public ResponseEntity agregarReserva(@PathVariable Integer id, @RequestBody Usuario user) throws CapacitateException {

        try {
            String dni = user.getDni();
            Curso unCurso = service.agregarReserva(id, dni);
            if(unCurso == null) {
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(unCurso);
            }
        } catch (CapacitateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/api/curso/{id}/inscripto")
    public ResponseEntity usuarioEstaInscripto(@PathVariable Integer id, @RequestBody Usuario user) {
        try {
            String dni = user.getDni();
            Boolean respuesta = service.usuarioEstaInscripto(id, dni);
            if(respuesta == null) {
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(respuesta);
            }
        } catch (CapacitateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/api/curso/{id}/cancelar")
    public ResponseEntity quitarReserva(@PathVariable Integer id, @RequestBody Usuario user) {
        try {
            String dni = user.getDni();
            Curso unCurso = service.quitarReserva(id, dni);
            if(unCurso == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(unCurso);
            }

        } catch (CapacitateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}

