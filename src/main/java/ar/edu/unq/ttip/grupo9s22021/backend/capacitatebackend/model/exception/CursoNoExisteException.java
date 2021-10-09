package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception;

public class CursoNoExisteException extends CapacitateException {
    public CursoNoExisteException() {
        super("El curso solicitado no existe.");
    }
}
