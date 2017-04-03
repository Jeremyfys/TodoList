package com.petrociencia.jeremy.com.petrociencia.modelo;

/**
 * Created by JEREMY on 25-03-2017.
 */

public class Usuario
{
   // public String idUsuario;

    public String nombreUsuario;

    public String tipo;

    public String contrasena;

    public String idUsuario;

    public Usuario(){

    }

    public Usuario(String idUsuario, String nombreUsuario, String contrasena, String tipo) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
