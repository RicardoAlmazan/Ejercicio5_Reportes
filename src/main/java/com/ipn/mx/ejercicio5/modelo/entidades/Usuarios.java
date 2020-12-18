package com.ipn.mx.ejercicio5.modelo.entidades;

public class Usuarios {
    private Integer idUsuario;
    private String nombre;
    private String paterno;
    private String materno;
    private String email;
    private Integer idTipoUsuario;

    public Usuarios(Integer idUsuario, String nombre, String paterno, String materno, String email, Integer idTipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.email = email;
        this.idTipoUsuario = idTipoUsuario;
    }

    public Usuarios() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    
    
}
