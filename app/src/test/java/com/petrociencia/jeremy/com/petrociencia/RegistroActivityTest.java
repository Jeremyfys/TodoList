package com.petrociencia.jeremy.com.petrociencia;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by JEREMY on 31-03-2017.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/app/src/main/AndroidManifest.xml")
public class RegistroActivityTest
{
    private Registro activity;

    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()

        activity = Robolectric.setupActivity(Registro.class);
    }

    @Test
    public void validateTextViewContent() {
        TextView tituloRegistro = (TextView) activity.findViewById(R.id.tituloRegistro);
        EditText inputNewUsername = (EditText) activity.findViewById(R.id.inputNewUser);
        EditText inputNewPassword = (EditText) activity.findViewById(R.id.inputNewPassword);
        Button buttonRegistrar = (Button) activity.findViewById(R.id.buttonRegistrar);
        Button buttonSalir = (Button) activity.findViewById(R.id.SalirRegistro);

        assertNotNull(tituloRegistro);
        assertNotNull(inputNewUsername);
        assertNotNull(inputNewPassword);
        assertNotNull(buttonRegistrar);
        assertNotNull(buttonSalir);

        assertTrue("TextView contains incorrect text",
                "Registrar Usuario".equals(tituloRegistro.getText().toString()));
        assertTrue("EditText contains incorrect text",
                "Username".equals(inputNewUsername.getHint().toString()));
        assertTrue("EditText contains incorrect text",
                "Password".equals(inputNewPassword.getHint().toString()));
        assertTrue("Button contains incorrect text",
                "Registrar".equals(buttonRegistrar.getText().toString()));
        assertTrue("Button contains incorrect text",
                "Salir".equals(buttonSalir.getText().toString()));
    }

    @Test
    public void registerUser() {

        EditText inputNewUsername = (EditText) activity.findViewById(R.id.inputNewUser);
        EditText inputNewPassword = (EditText) activity.findViewById(R.id.inputNewPassword);
        Button buttonRegistrar = (Button) activity.findViewById(R.id.buttonRegistrar);


        assertNotNull(inputNewUsername);
        assertNotNull(inputNewPassword);
        assertNotNull(buttonRegistrar);

        inputNewUsername.setText("Marco");
        inputNewPassword.setText("123456");


        activity.findViewById(R.id.buttonRegistrar).performClick();



        assertEquals("", inputNewUsername.getText().toString());
        assertEquals("", inputNewPassword.getText().toString());

    }

    @Test
    public void registerUserExistente() {

        EditText inputNewUsername = (EditText) activity.findViewById(R.id.inputNewUser);
        EditText inputNewPassword = (EditText) activity.findViewById(R.id.inputNewPassword);
        Button buttonRegistrar = (Button) activity.findViewById(R.id.buttonRegistrar);


        assertNotNull(inputNewUsername);
        assertNotNull(inputNewPassword);
        assertNotNull(buttonRegistrar);

        inputNewUsername.setText("Marco");
        inputNewPassword.setText("123456");


        activity.findViewById(R.id.buttonRegistrar).performClick();

        assertEquals("", inputNewUsername.getText().toString());
        assertEquals("", inputNewPassword.getText().toString());

        inputNewUsername.setText("Marco");
        inputNewPassword.setText("123456");

        activity.findViewById(R.id.buttonRegistrar).performClick();

        assertTrue(" User Exists ", "Marco".equals(inputNewUsername.getText().toString()));


    }

    @Test
    public void registerFailedEmptyInput() {

        EditText inputNewUsername = (EditText) activity.findViewById(R.id.inputNewUser);
        EditText inputNewPassword = (EditText) activity.findViewById(R.id.inputNewPassword);
        Button buttonRegistrar = (Button) activity.findViewById(R.id.buttonRegistrar);


        assertNotNull(inputNewUsername);
        assertNotNull(inputNewPassword);
        assertNotNull(buttonRegistrar);

        inputNewUsername.setText("");
        inputNewPassword.setText("");


        activity.findViewById(R.id.buttonRegistrar).performClick();

        assertEquals("", inputNewUsername.getText().toString());
        assertEquals("", inputNewPassword.getText().toString());

    }
}
