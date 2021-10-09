package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception;

public class CursoYaComenzoException extends CapacitateException {
    public CursoYaComenzoException() {
        super("Las inscripciones a este curso ya terminaron.");
    }
}
