package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model;



import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.CursoLlenoException;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.CursoYaComenzoException;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.UsuarioYaReservoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CursoTest {

    private List<String> reservas = new ArrayList<String>();
    private LocalDate fecha = LocalDate.ofYearDay(2025,200);
    private LocalDate fechaPasada = LocalDate.ofYearDay(2020, 200);
    
    private Curso curso = new Curso(1, "Curso Test", 0, "Nombre docente",
            "Direccion", "Quilmes", reservas, 10, fecha);

    private Curso cursoLleno = new Curso(2, "Curso Test 2", 0, "Nombre docente",
                    "Direccion", "Bernal", reservas, 0, fecha);

    private Curso cursoYaIniciado = new Curso(3, "Curso Test 3", 0, "Nombre docente",
            "Direccion", "Bernal", reservas, 10, fechaPasada);

    private String unDni = "123456789";

    @Test
    public void cursoVacioTest() {
        assertTrue(curso.hayCuposDisponibles());
        assertFalse(curso.terminaronInscripciones());
        assertTrue(curso.esGratuito());
    }

    @Test
    public void noEsPosibleInscribirseAUnCursoLleno() {

        Assertions.assertThrows(CursoLlenoException.class, () -> {
            cursoLleno.reservarCupo(unDni);
        });

    }

    @Test
    public void esPosibleInscribirseAUnCursoConCupos() throws CursoLlenoException, UsuarioYaReservoException, CursoYaComenzoException {
        curso.reservarCupo(unDni);
        assertEquals(curso.getReservas().size(), 1);
    }

    @Test
    public void noEsPosibleInscribirseAUnCursoQueYaInicio() {
        Assertions.assertThrows(CursoYaComenzoException.class, () -> {
            cursoYaIniciado.reservarCupo(unDni);
        });
    }

    @Test
    public void noEsPosibleReservarMasDeUnCupoConElMismoDni() {
        Assertions.assertThrows(UsuarioYaReservoException.class, () -> {
            curso.reservarCupo(unDni);
            curso.reservarCupo(unDni);
        });
    }
}
