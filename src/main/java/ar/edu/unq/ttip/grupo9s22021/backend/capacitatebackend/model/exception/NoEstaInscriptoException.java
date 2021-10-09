package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception;

public class NoEstaInscriptoException extends CapacitateException{
    public NoEstaInscriptoException(String dni) {
        super("No hay una reserva con el dni " + dni + " en este curso.");
    }
}
