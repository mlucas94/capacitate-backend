package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model;

import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.CursoLlenoException;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.CursoYaComenzoException;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.NoEstaInscriptoException;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.exception.UsuarioYaReservoException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String nombre;

    @Column
    private Integer costo;

    @Column
    private String descripcion;

    @Column
    private String docente;

    @Column
    private String direccion;

    @Column
    private String localidad;

    @Column
    private LocalDate fechaInicio;

    @ElementCollection
    @CollectionTable(
            name="DNI_TABLE",
            joinColumns = @JoinColumn(name="reserva")
    )
    private List<String> reservas;

    @Column
    private Integer cupos;

    public Curso() {
        super();
    }

    public Curso(Integer id, String nombre, Integer costo, String docente, String direccion,
                 String localidad, List<String> cuposReservados, Integer cupos, LocalDate fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.docente = docente;
        this.direccion = direccion;
        this.localidad = localidad;
        this.reservas = cuposReservados;
        this.cupos = cupos;
        this.fechaInicio = fechaInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public boolean esGratuito() {
        return this.costo == 0;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDocenteACargo() {
        return docente;
    }

    public void setDocenteACargo(String docenteACargo) {
        this.docente = docenteACargo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public List<String> getReservas() {
        return reservas;
    }

    public void setReservas(List<String> cuposReservados) {
        this.reservas = cuposReservados;
    }

    public void reservarCupo(String dni) throws CursoLlenoException, UsuarioYaReservoException, CursoYaComenzoException {
        if(puedeReservarCupo(dni)) {
            this.reservas.add(dni);
        }
    }

    public boolean puedeReservarCupo(String dni) throws CursoLlenoException, CursoYaComenzoException, UsuarioYaReservoException {
        if(!hayCuposDisponibles()) {
            throw new CursoLlenoException();
        } else if(terminaronInscripciones()) {
            throw new CursoYaComenzoException();
        } else if(estaInscripto(dni)) {
            throw new UsuarioYaReservoException(dni);
        } else {
            return true;
        }
    }

    public void cancelarReserva(String dni) throws NoEstaInscriptoException, CursoYaComenzoException {
        if(puedeCancelarReserva(dni)) {
            this.reservas.remove(dni);
        }
    }

    public boolean puedeCancelarReserva(String dni) throws NoEstaInscriptoException, CursoYaComenzoException {
        if(terminaronInscripciones()) {
            throw new CursoYaComenzoException();
        } else if (!estaInscripto(dni)) {
            throw new NoEstaInscriptoException(dni);
        } else {
            return true;
        }
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

    public boolean hayCuposDisponibles() {
        return this.reservas.size() < this.cupos ;
    }

    public Integer getCuposDisponibles() { return this.cupos - this.reservas.size(); }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean terminaronInscripciones() { return LocalDate.now().isAfter(this.fechaInicio); }

    public boolean estaInscripto(String dni) { return this.reservas.contains(dni); }
}


