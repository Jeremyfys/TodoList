package com.petrociencia.jeremy.com.petrociencia.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.petrociencia.jeremy.com.petrociencia.sqlite.UserNote.Notas;
import com.petrociencia.jeremy.com.petrociencia.sqlite.UserNote.Usuario;

/**
 * Created by JEREMY on 25-03-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final Context contexto;



    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "todolist";

    public interface Tablas {
        String USUARIOS = "usuarios";
        String NOTAS = "notas";
    }

    interface Referencias {

        String ID_NOTE_USER = String.format("REFERENCES %s(%s)", Tablas.USUARIOS, Usuario.ID_USER);

    }



    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    public DBHelper(Context contexto) {
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," + "%s TEXT NOT NULL,%s TEXT NOT NULL,%s INTEGER NOT NULL)",
                Tablas.USUARIOS, Usuario.ID_USER, Usuario.NAME_USER, Usuario.PASSWORD, Usuario.TIPO_USER));


        db.execSQL(String.format("INSERT INTO %s values(1,'admin','admin',0)", Tablas.USUARIOS));


        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," + "%s TEXT NOT NULL,%s INTEGER , %s INTEGER NOT NULL %s)",
                Tablas.NOTAS, Notas.ID_NOTE, Notas.TEXTO, Notas.TERMINADO, Notas.ID_USER_NOTE, Referencias.ID_NOTE_USER));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.NOTAS);

        onCreate(db);

    }
}
