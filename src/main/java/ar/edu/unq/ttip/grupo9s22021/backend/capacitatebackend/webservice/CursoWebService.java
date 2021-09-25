package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.webservice;

import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.Curso;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class CursoWebService {

    @Autowired
    public CursoService service;

    @GetMapping("/api/curso/{id}")
    public ResponseEntity<Curso> byCursoId(@PathVariable Integer id) {
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
    public ResponseEntity<Curso> agregarReserva(@PathVariable Integer id) throws Exception {
        Curso unCurso = service.agregarReserva(id);
        if(unCurso == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(unCurso);
        }
    }
}
