package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception;

public class UsuarioYaReservoException extends CapacitateException {
    public UsuarioYaReservoException(String dni) {
        super("Ya se realizo una reserva con el dni "
                + dni + " en este curso.");
    }
}
