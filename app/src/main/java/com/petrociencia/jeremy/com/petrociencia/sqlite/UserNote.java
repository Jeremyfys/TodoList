package com.petrociencia.jeremy.com.petrociencia.sqlite;

/**
 * Created by JEREMY on 25-03-2017.
 */

public class UserNote
{
    public interface Usuario
    {
         String ID_USER = "id";
         String NAME_USER = "nombre";
         String PASSWORD = "contrasena";
         String TIPO_USER = "tipo";
    }

    public interface Notas
    {
        String ID_NOTE = "id_note";
        String ID_USER_NOTE = "id_usuario";
        String TEXTO = "texto";
        String TERMINADO = "terminado";
    }
    private UserNote(){

    }
}
