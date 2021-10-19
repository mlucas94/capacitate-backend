package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(unique = true)
    private String nombreDeUsuario;

    @Column(unique = true)
    private String mail;

    @Column(unique = true)
    private String dni;

    @Column
    @JsonIgnore
    private String password;

    public Usuario() { super(); }

    public Usuario(Integer id, String nombreDeUsuario, String mail, String dni, String password) {
        this.id = id;
        this.nombreDeUsuario = nombreDeUsuario;
        this.mail = mail;
        this.dni = dni;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
