package com.petrociencia.jeremy.com.petrociencia.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.petrociencia.jeremy.com.petrociencia.modelo.Notas;
import com.petrociencia.jeremy.com.petrociencia.modelo.Usuario;

/**
 * Created by JEREMY on 25-03-2017.
 */

public final class DBConsultas
{
    private static DBHelper baseDatos;

    private static DBConsultas instancia = new DBConsultas();

    private DBConsultas() {

    }

    public static DBConsultas obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new DBHelper(contexto);
        }
        return instancia;
    }

   /* public Cursor obtenerUsuario(String nombre) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                DBHelper.Tablas.USUARIOS, UserNote.Usuario.NAME_USER);

        String[] selectionArgs = {nombre};

        return db.rawQuery(sql, selectionArgs);

    }

    public Cursor obtenerUsuarios() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT * FROM %s", DBHelper.Tablas.USUARIOS);

        return db.rawQuery(sql, null);
    }*/

    public int insertarUsuario(Usuario usuario)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(UserNote.Usuario.NAME_USER, usuario.nombreUsuario);
        valores.put(UserNote.Usuario.PASSWORD, usuario.contrasena);
        valores.put(UserNote.Usuario.TIPO_USER, Integer.parseInt(usuario.tipo));

        Log.d("LOG revisar", String.format("Valores insert %s ,  %s,  %s", usuario.nombreUsuario, usuario.contrasena, usuario.tipo));

        // Insertar cabecera
        return db.insertOrThrow(DBHelper.Tablas.USUARIOS, null, valores) > 0 ? 1 : 2;

    }
    public String insertarNota(Notas notas)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(UserNote.Notas.ID_USER_NOTE, notas.idUser);
        valores.put(UserNote.Notas.TEXTO, notas.texto);
        valores.put(UserNote.Notas.TERMINADO, notas.terminado);

        Log.d("LOG revisar NOTAS", String.format("Valores insert %d ,  %s,  %d", notas.idUser, notas.texto, notas.terminado));


        return db.insertOrThrow(DBHelper.Tablas.NOTAS, null, valores) > 0 ? "Nota creada" : "No se pudo crear la nota";

    }

    public Cursor extraerNotas(String idUser)
    {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor fila;

        String sql = String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                UserNote.Notas.ID_NOTE, UserNote.Notas.TEXTO, UserNote.Notas.TERMINADO, DBHelper.Tablas.NOTAS, UserNote.Notas.ID_USER_NOTE);

        String[] selectionArgs = {idUser};

        fila = db.rawQuery(sql, selectionArgs);

        return fila;
    }

    public String actualizarNotas(String idNota, String idUser, String texto)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( UserNote.Notas.TEXTO , texto);
        String[] selectionArgs = {idNota, idUser};
        return db.update( DBHelper.Tablas.NOTAS , contentValues,
                String.format("%s = ? AND %s = ?", UserNote.Notas.ID_NOTE, UserNote.Notas.ID_USER_NOTE) ,
                selectionArgs) > 0 ? "Nota editada" : "No se pudo editar la nota";
    }

    public String actualizarEstado(String idNota, String idUser)
{
    SQLiteDatabase db = baseDatos.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put( UserNote.Notas.TERMINADO , 1);
    String[] selectionArgs = {idNota, idUser};
    return db.update( DBHelper.Tablas.NOTAS , contentValues,
            String.format("%s = ? AND %s = ?", UserNote.Notas.ID_NOTE, UserNote.Notas.ID_USER_NOTE) ,
            selectionArgs) > 0 ? "Tarea Finalizada" : "No se pudo finalizar la tarea";
}

    public String eliminarNota(String idNota, String isUser)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        db.beginTransaction();
        db.delete(DBHelper.Tablas.NOTAS,
                String.format("%s = ? AND %s = ?", UserNote.Notas.ID_NOTE, UserNote.Notas.ID_USER_NOTE),
                new String[] {idNota, isUser});
        db.setTransactionSuccessful();
        db.endTransaction();
        return "Nota eliminada";
    }



    public boolean verificarExistente(String nombre)
    {

        Cursor fila;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql =  String.format("SELECT %s FROM %s WHERE %s = ?", UserNote.Usuario.NAME_USER, DBHelper.Tablas.USUARIOS, UserNote.Usuario.NAME_USER);

        String[] selectionArgs = {nombre};

        fila = db.rawQuery(sql, selectionArgs);

        if (fila.moveToFirst())
        {
            String usua = fila.getString(0);
            Log.d("FIELDS", String.format("VALORES VERF %s %s", usua, nombre));

            return true;
        }

        return false;

    }
    public Usuario verificarUsuario(String nombre, String contrasena)
    {
        Cursor fila;

        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT %s ,%s, %s, %s FROM %s WHERE %s = ? AND %s = ?",
                UserNote.Usuario.NAME_USER, UserNote.Usuario.PASSWORD, UserNote.Usuario.ID_USER,
                UserNote.Usuario.TIPO_USER,DBHelper.Tablas.USUARIOS,  UserNote.Usuario.NAME_USER, UserNote.Usuario.PASSWORD);

        String[] selectionArgs = {nombre, contrasena};
        Log.d("LOG revisar", String.format("Valores %s ,  %s", nombre, contrasena));
        fila = db.rawQuery(sql, selectionArgs);
        Usuario u;
        if (fila.moveToFirst())
        {
            String usua = fila.getString(0);
            String pass = fila.getString(1);
            String id = Integer.toString(fila.getInt(2));
            String tipo = Integer.toString(fila.getInt(3));

           u = new Usuario(id, usua, pass, tipo);

            Log.d("FIELDS", String.format("VALORES %s %s %s %s", id, usua, pass, tipo));

            if(nombre.equals(usua) && contrasena.equals(pass))
            {
                if(nombre.equals("admin") && contrasena.equals("admin"))
                {

                    return u;
                }

                return u;
            }
        }
        u = new Usuario("2","2","2","2");

        return u;

    }




}
