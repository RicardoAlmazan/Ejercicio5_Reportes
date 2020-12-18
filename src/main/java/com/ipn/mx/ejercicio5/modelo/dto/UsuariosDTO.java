package com.ipn.mx.ejercicio5.modelo.dto;

import com.ipn.mx.ejercicio5.modelo.entidades.Usuarios;

public class UsuariosDTO {
    private Usuarios entidad;

    public UsuariosDTO() {
        this.entidad = new Usuarios();
    }

    public Usuarios getEntidad() {
        return entidad;
    }

    public void setEntidad(Usuarios entidad) {
        this.entidad = entidad;
    }

}
