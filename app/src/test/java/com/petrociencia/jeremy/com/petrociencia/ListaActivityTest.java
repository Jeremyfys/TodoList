package com.petrociencia.jeremy.com.petrociencia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.petrociencia.jeremy.com.petrociencia.modelo.Notas;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.robolectric.RuntimeEnvironment.application;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by JEREMY on 01-04-2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/app/src/main/AndroidManifest.xml")
public class ListaActivityTest {

    private ActivityController<Lista> controller;
    Lista activity;
    private Notas notas;



    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()

        controller = Robolectric.buildActivity(Lista.class);



    }

    private void createWithIntent() {
        Intent intent = new Intent(application, Lista.class);
        intent.putExtra("id", "1");
        intent.putExtra("nombre", "jeremy");
        activity = controller
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void addNote() {
        createWithIntent();

        MenuItem item = new RoboMenuItem(R.id.action_add_task);
        activity.onOptionsItemSelected(item);
        ListView mTaskListView = (ListView) activity.findViewById(R.id.list_todo);
        assertNotNull(mTaskListView);

        final EditText taskEditText = new EditText(activity);
        taskEditText.setText("Prueba");

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(alert);
        assertThat(alert.isShowing(), equalTo(true));
        assertThat(((String) shadowOf(alert).getTitle()), equalTo("Agregar nueva tarea"));
        assertThat(((String) shadowOf(alert).getMessage()), equalTo("Que haras despues?"));
        alert.setView(taskEditText);
        alert.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
        assertEquals(1, mTaskListView.getCount());

    }

    @Test
    public void deleteNote() {
        createWithIntent();

        MenuItem item = new RoboMenuItem(R.id.action_add_task);
        activity.onOptionsItemSelected(item);
        ListView mTaskListView = (ListView) activity.findViewById(R.id.list_todo);
        assertNotNull(mTaskListView);

        final EditText taskEditText = new EditText(activity);
        taskEditText.setText("Prueba");

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(alert);
        assertThat(alert.isShowing(), equalTo(true));
        assertThat(((String) shadowOf(alert).getTitle()), equalTo("Agregar nueva tarea"));
        assertThat(((String) shadowOf(alert).getMessage()), equalTo("Que haras despues?"));
        alert.setView(taskEditText);
        alert.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
        assertEquals(1, mTaskListView.getCount());

        mTaskListView.getAdapter().getView(0, null, null).findViewById(R.id.task_delete).performClick();

        assertEquals(0, mTaskListView.getCount());

    }

    @Test
    public void editNote() {
        createWithIntent();

        MenuItem item = new RoboMenuItem(R.id.action_add_task);
        activity.onOptionsItemSelected(item);
        ListView mTaskListView = (ListView) activity.findViewById(R.id.list_todo);
        assertNotNull(mTaskListView);

        final EditText taskEditText = new EditText(activity);
        final EditText taskEditTextEdit = new EditText(activity);

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(alert);
        assertThat(alert.isShowing(), equalTo(true));
        assertThat(((String) shadowOf(alert).getTitle()), equalTo("Agregar nueva tarea"));
        assertThat(((String) shadowOf(alert).getMessage()), equalTo("Que haras despues?"));
        alert.setContentView(taskEditText);
        taskEditText.setText("Prueba");
        assertEquals("Prueba".trim(),taskEditText.getText().toString().trim());
        alert.getButton(DialogInterface.BUTTON_POSITIVE).performClick();

        assertEquals(1, mTaskListView.getCount());

        mTaskListView.getAdapter().getView(0, null, null).findViewById(R.id.task_edit).performClick();

        AlertDialog alertEdit = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(alertEdit);
        assertThat(alertEdit.isShowing(), equalTo(true));
        assertThat(((String) shadowOf(alertEdit).getTitle()), equalTo("Editar tarea"));
        assertThat(((String) shadowOf(alertEdit).getMessage()), equalTo("Algun cambio?"));
        alertEdit.setContentView(taskEditTextEdit);
        taskEditTextEdit.setText("Cambio");

        alertEdit.getButton(DialogInterface.BUTTON_POSITIVE).performClick();


        assertNotEquals("Prueba", mTaskListView.getAdapter().getView(0, null, null).findViewById(R.id.task_title).toString());

    }
    @Test
    public void changeStatus() {
        createWithIntent();

        MenuItem item = new RoboMenuItem(R.id.action_add_task);
        activity.onOptionsItemSelected(item);
        ListView mTaskListView = (ListView) activity.findViewById(R.id.list_todo);
        assertNotNull(mTaskListView);

        final EditText taskEditText = new EditText(activity);


        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(alert);
        assertThat(alert.isShowing(), equalTo(true));
        assertThat(((String) shadowOf(alert).getTitle()), equalTo("Agregar nueva tarea"));
        assertThat(((String) shadowOf(alert).getMessage()), equalTo("Que haras despues?"));
        alert.setContentView(taskEditText);
        taskEditText.setText("Prueba");
        assertEquals("Prueba".trim(),taskEditText.getText().toString().trim());
        alert.getButton(DialogInterface.BUTTON_POSITIVE).performClick();

        assertEquals(1, mTaskListView.getCount());

        mTaskListView.getAdapter().getView(0, null, null).findViewById(R.id.task_confirm).performClick();

        assertFalse("No es clickeable", mTaskListView.getAdapter().getView(0, null, null).findViewById(R.id.task_confirm).isClickable());


    }


}
