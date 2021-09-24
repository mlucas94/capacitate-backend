package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CursoTest {

    private List<String> reservas = new ArrayList<String>();
    private LocalDate fecha = LocalDate.ofYearDay(2024,200);
    
    private Curso curso = new Curso(1, "Curso Test", 0, "Nombre docente",
            "Direccion", "Quilmes", reservas, 10, fecha);

    private Curso cursoLleno = new Curso(2, "Curso Test 2", 0, "Nombre docente",
                    "Direccion", "Bernal", reservas, 0, fecha);

    @Test
    public void cursoVacioTest() {
        assertTrue(curso.hayCuposDisponibles());
        assertFalse(curso.terminaronInscripciones());
        assertTrue(curso.esGratuito());
    }

    @Test
    public void noEsPosibleInscribirseAUnCursoLleno() throws Exception {

        Assertions.assertThrows(Exception.class, () -> {
            cursoLleno.reservarCupo("123456789");
        });

    }

    @Test
    public void esPosibleInscribirseAUnCursoConCupos() throws Exception {
        curso.reservarCupo("123456789");
        assertEquals(curso.getReservas().size(), 1);
    }
}
