package com.petrociencia.jeremy.com.petrociencia;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by JEREMY on 31-03-2017.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/app/src/main/AndroidManifest.xml")
public class LoginActivityTest {

    private Login activity;

    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()

        activity = Robolectric.setupActivity(Login.class);
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    public void validateTextViewContent() {
        TextView tituloAcceso = (TextView) activity.findViewById(R.id.tituloAcceso);
        EditText inputUsername = (EditText) activity.findViewById(R.id.inputUsername);
        EditText inputPassword = (EditText) activity.findViewById(R.id.inputPassword);
        Button buttonLogin = (Button) activity.findViewById(R.id.buttonlogin);

        assertNotNull(tituloAcceso);
        assertNotNull(inputUsername);
        assertNotNull(inputPassword);
        assertNotNull(buttonLogin);

        assertTrue("TextView contains incorrect text",
                "Acceso".equals(tituloAcceso.getText().toString()));
        assertTrue("EditText contains incorrect text",
                "Username".equals(inputUsername.getHint().toString()));
        assertTrue("EditText contains incorrect text",
                "Password".equals(inputPassword.getHint().toString()));
        assertTrue("Button contains incorrect text",
                "Login".equals(buttonLogin.getText().toString()));
    }

    @Test
    public void registroStartedLoginButtonClick() {

        EditText inputUsername = (EditText) activity.findViewById(R.id.inputUsername);
        EditText inputPassword = (EditText) activity.findViewById(R.id.inputPassword);

        assertNotNull(inputUsername);
        assertNotNull(inputPassword);

        inputUsername.setText("admin");
        inputPassword.setText("admin");


        activity.findViewById(R.id.buttonlogin).performClick();

        assertEquals("admin", inputUsername.getText().toString());
        assertEquals("admin", inputPassword.getText().toString());

        // The intent we expect to be launched when a user clicks on the button
        Intent expectedIntent = new Intent(activity, Registro.class);

        // An Android "Activity" doesn't expose a way to find out about activities it launches
        // Robolectric's "ShadowActivity" keeps track of all launched activities and exposes this information
        // through the "getNextStartedActivity" method.
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        // Determine if two intents are the same for the purposes of intent resolution (filtering).
        // That is, if their action, data, type, class, and categories are the same. This does
        // not compare any extra data included in the intents
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @Test
    public void registroStartedFailedLoginButtonClick() {

        EditText inputUsername = (EditText) activity.findViewById(R.id.inputUsername);
        EditText inputPassword = (EditText) activity.findViewById(R.id.inputPassword);

        assertNotNull(inputUsername);
        assertNotNull(inputPassword);

        inputUsername.setText("admin");
        inputPassword.setText("123456");


        activity.findViewById(R.id.buttonlogin).performClick();

        assertFalse("Wrong Password or Username ", "admin".equals(inputPassword.getText().toString()) || "admin".equals(inputPassword.getText().toString()));

    }

    @Test
    public void registroStartedFailedEmptyInputLoginButtonClick() {

        EditText inputUsername = (EditText) activity.findViewById(R.id.inputUsername);
        EditText inputPassword = (EditText) activity.findViewById(R.id.inputPassword);

        assertNotNull(inputUsername);
        assertNotNull(inputPassword);

        inputUsername.setText("");
        inputPassword.setText("");


        activity.findViewById(R.id.buttonlogin).performClick();

        assertFalse(" Empty Username or Password ", "admin".equals(inputUsername.getText().toString()) || "admin".equals(inputPassword.getText().toString()) );

    }
}
