package com.petrociencia.jeremy.com.petrociencia.modelo;

/**
 * Created by JEREMY on 25-03-2017.
 */

public class Notas {
    public int idNota;

    public String texto;

    public int idUser;

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getTerminado() {
        return terminado;
    }

    public void setTerminado(int terminado) {
        this.terminado = terminado;
    }

    public int terminado;


    public Notas(int idNota, String texto,
                   int idUser, int terminado) {
        this.terminado = terminado;
        this.idNota = idNota;
        this.texto = texto;
        this.idUser = idUser;

    }

    public Notas()
    {

    }
}
