package com.petrociencia.jeremy.com.petrociencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.petrociencia.jeremy.com.petrociencia.modelo.Notas;
import com.petrociencia.jeremy.com.petrociencia.modelo.Usuario;
import com.petrociencia.jeremy.com.petrociencia.sqlite.DBConsultas;
import com.petrociencia.jeremy.com.petrociencia.sqlite.DBHelper;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by JEREMY on 30-03-2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/app/src/main/AndroidManifest.xml")
public class DBTestConsultas extends TestCase {

    private Context context;
    private DBHelper helper;
    private SQLiteDatabase db;
    private Notas notas;
    private Usuario user;
    private DBConsultas dbConsultas;

    @Before
    public void setUp() throws Exception {
        context = RuntimeEnvironment.application;
        helper = new DBHelper(context);
        dbConsultas = DBConsultas.obtenerInstancia(context);
        db = helper.getReadableDatabase();

    }

    private void setupNota() {
        notas = new Notas();
        notas.setTexto("Prueba");
        notas.setIdNota(1);
        notas.setIdUser(1);
        notas.setTerminado(0);
    }

    private void setupUser() {
        user = new Usuario();
        user.setNombreUsuario("Marco");
        user.setContrasena("123456");
        user.setTipo("1");

    }

    @Test
    public void insertUser() {
        setupUser();
        int usuario = dbConsultas.insertarUsuario(user);
        assertNotNull(usuario);
        assertEquals(1, usuario);
    }

    @Test
    public void verifyUser() {
        setupUser();
        int insert = dbConsultas.insertarUsuario(user);
        assertNotNull(insert);
        assertEquals(1, insert);
        Usuario usuario = dbConsultas.verificarUsuario(user.getNombreUsuario(), user.getContrasena());
        assertNotNull(usuario);
        assertEquals(usuario.getNombreUsuario(), user.getNombreUsuario());
        assertEquals(usuario.getContrasena(), user.getContrasena());
    }
    @Test
    public void verifyUserNull() {
        setupUser();
        int insert = dbConsultas.insertarUsuario(user);
        assertNotNull(insert);
        assertEquals(1, insert);
        user.setNombreUsuario("Jeremy");
        Usuario usuario = dbConsultas.verificarUsuario(user.getNombreUsuario(), user.getContrasena());
        assertNotNull(usuario);
        assertEquals(usuario.getNombreUsuario(), "2");
    }
    @Test
    public void verifyUserExist() {
        setupUser();
        boolean var1 = dbConsultas.verificarExistente(user.getNombreUsuario());
        assertEquals(var1, false);
        int insert1 = dbConsultas.insertarUsuario(user);
        assertNotNull(insert1);
        assertEquals(1, insert1);
        boolean var2 = dbConsultas.verificarExistente(user.getNombreUsuario());
        assertEquals(var2, true);
        user.setNombreUsuario("Jeremy");
        int insert2 = dbConsultas.insertarUsuario(user);
        assertNotNull(insert2);
        assertEquals(1, insert2);
    }
    @Test
    public void verifyUserNoExist() {
        setupUser();
        boolean var = dbConsultas.verificarExistente(user.getNombreUsuario());
        assertEquals(var, false);
        int insert = dbConsultas.insertarUsuario(user);
        assertNotNull(insert);
        assertEquals(1, insert);
    }

    @Test
    public void insertNote() {
        setupNota();
        setupUser();
        int usuario = dbConsultas.insertarUsuario(user);
        assertNotNull(usuario);
        assertEquals(1, usuario);
        String nota = dbConsultas.insertarNota(notas);
        assertNotNull(nota);
        assertEquals("Nota creada", nota);
    }
    @Test
    public void extractNote() {
        setupNota();
        setupUser();
        Cursor cursor;
        int usuario = dbConsultas.insertarUsuario(user);
        assertNotNull(usuario);
        assertEquals(1, usuario);
        String nota = dbConsultas.insertarNota(notas);
        assertNotNull(nota);
        assertEquals("Nota creada", nota);
        cursor = dbConsultas.extraerNotas(Integer.toString(notas.getIdUser()));
        assertNotNull(cursor);


    }

    @Test
    public void deleteNote() {
        setupNota();
        setupUser();

        int usuario = dbConsultas.insertarUsuario(user);
        assertNotNull(usuario);
        assertEquals(1, usuario);
        String nota = dbConsultas.insertarNota(notas);
        assertNotNull(nota);
        assertEquals("Nota creada", nota);
        String val = dbConsultas.eliminarNota(Integer.toString(notas.getIdNota()),Integer.toString(notas.getIdUser()));
        assertNotNull(val);
        assertEquals("Nota eliminada", val);

    }
    @Test
    public void updateNote() {
        setupNota();
        setupUser();

        int usuario = dbConsultas.insertarUsuario(user);
        assertNotNull(usuario);
        assertEquals(1, usuario);
        String nota = dbConsultas.insertarNota(notas);
        assertNotNull(nota);
        assertEquals("Nota creada", nota);
        String val = dbConsultas.actualizarNotas(Integer.toString(notas.getIdNota()),Integer.toString(notas.getIdUser()),"Cambio");
        assertNotNull(val);
        assertEquals("Nota editada", val);

    }
    @Test
    public void updateNoteStatus() {
        setupNota();
        setupUser();

        int usuario = dbConsultas.insertarUsuario(user);
        assertNotNull(usuario);
        assertEquals(1, usuario);
        String nota = dbConsultas.insertarNota(notas);
        assertNotNull(nota);
        assertEquals("Nota creada", nota);
        String val = dbConsultas.actualizarEstado(Integer.toString(notas.getIdNota()),Integer.toString(notas.getIdUser()));
        assertNotNull(val);
        assertEquals("Tarea Finalizada", val);

    }



}
