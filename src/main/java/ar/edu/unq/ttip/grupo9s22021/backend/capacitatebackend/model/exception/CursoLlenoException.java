package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception;

public class CursoLlenoException extends CapacitateException {
    public CursoLlenoException() {
        super("El curso no tiene mas cupos disponibles.");
    }
}
