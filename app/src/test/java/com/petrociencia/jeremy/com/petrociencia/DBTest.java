package com.petrociencia.jeremy.com.petrociencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.petrociencia.jeremy.com.petrociencia.sqlite.DBHelper;
import com.petrociencia.jeremy.com.petrociencia.sqlite.UserNote;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.petrociencia.jeremy.com.petrociencia.sqlite.DBHelper.Tablas.NOTAS;
import static com.petrociencia.jeremy.com.petrociencia.sqlite.DBHelper.Tablas.USUARIOS;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.junit.Assert.assertThat;


/**
 * Created by JEREMY on 30-03-2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/app/src/main/AndroidManifest.xml")
public class DBTest extends TestCase {


    private Context context;
    private DBHelper helper;
    private SQLiteDatabase db;

    @Before
    public void setUp() throws Exception {
        context = RuntimeEnvironment.application;
        helper = new DBHelper(context);
        db = helper.getReadableDatabase();

    }

    @Test
    public void testDBCreated()
    {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        // Verify is the DB is opening correctly
        assertTrue("DB didn't open", db.isOpen());
        db.close();
    }

    @Test
    public void testDBColsUser() {
        Cursor c = db.query(USUARIOS, null, null, null, null, null, null);
        assertNotNull(c);

        String[] cols = c.getColumnNames();
        assertThat("Column not implemented: " + UserNote.Usuario.ID_USER,
                cols, hasItemInArray(UserNote.Usuario.ID_USER));
        assertThat("Column not implemented: " + UserNote.Usuario.NAME_USER,
                cols, hasItemInArray(UserNote.Usuario.NAME_USER));
        assertThat("Column not implemented: " + UserNote.Usuario.PASSWORD,
                cols, hasItemInArray(UserNote.Usuario.PASSWORD));
        assertThat("Column not implemented: " + UserNote.Usuario.TIPO_USER,
                cols, hasItemInArray(UserNote.Usuario.TIPO_USER));

        c.close();
    }

    @Test
    public void testDBColsNote() {
        Cursor c = db.query(NOTAS, null, null, null, null, null, null);
        assertNotNull(c);

        String[] cols = c.getColumnNames();
        assertThat("Column not implemented: " + UserNote.Notas.ID_NOTE,
                cols, hasItemInArray(UserNote.Notas.ID_NOTE));
        assertThat("Column not implemented: " + UserNote.Notas.ID_USER_NOTE,
                cols, hasItemInArray(UserNote.Notas.ID_USER_NOTE));
        assertThat("Column not implemented: " + UserNote.Notas.TEXTO,
                cols, hasItemInArray(UserNote.Notas.TEXTO));
        assertThat("Column not implemented: " + UserNote.Notas.TERMINADO,
                cols, hasItemInArray(UserNote.Notas.TERMINADO));

        c.close();
    }
    @After
    public void cleanup(){
        db.close();
    }

}