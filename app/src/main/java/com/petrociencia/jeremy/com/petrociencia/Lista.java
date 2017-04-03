package com.petrociencia.jeremy.com.petrociencia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.petrociencia.jeremy.com.petrociencia.modelo.Notas;
import com.petrociencia.jeremy.com.petrociencia.sqlite.DBConsultas;
import com.petrociencia.jeremy.com.petrociencia.sqlite.UserNote;

import java.util.ArrayList;

import static com.petrociencia.jeremy.com.petrociencia.R.id.action_add_task;
import static com.petrociencia.jeremy.com.petrociencia.R.id.action_salir;
//import static com.petrociencia.jeremy.com.petrociencia.sqlite.UserNote.Notas.TEXTO;


public class Lista extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    String idUser;
    String nombreUser;
    DBConsultas datos;
    String mensaje;
    Cursor cursor;
    private ListView mTaskListView;
    CustomAdapter custom1;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        idUser = getIntent().getExtras().getString("id");
        nombreUser = getIntent().getExtras().getString("nombre");
        mTaskListView = (ListView) findViewById(R.id.list_todo);
        datos = DBConsultas.obtenerInstancia(getApplicationContext());
        updateUI(idUser);


        Log.d("Valores ACT", String.format("Valores %s  %s", idUser, nombreUser));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case action_add_task:
                Log.d(TAG, "Add a new task");

                final EditText taskEditText = new EditText(this);

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Agregar nueva tarea")
                        .setMessage("Que haras despues?")
                        .setView(taskEditText).setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                Log.d(TAG, "Task to add: " + task);

                                mensaje = datos.insertarNota(new Notas(1,task, Integer.parseInt(idUser), 0));
                                updateUI(idUser);
                                makeToast(mensaje);
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create();
                dialog.show();

                return true;
            case action_salir:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void makeToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    private void updateUI(String idUser) {
        ArrayList<Notas> taskList = new ArrayList<Notas>();
        cursor = datos.extraerNotas(idUser);
        while(cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(UserNote.Notas.TEXTO);
            int idx2 = cursor.getColumnIndex(UserNote.Notas.ID_NOTE);
            int idx3  = cursor.getColumnIndex(UserNote.Notas.TERMINADO);

            taskList.add(new Notas (cursor.getInt(idx2), cursor.getString(idx), Integer.parseInt(idUser), cursor.getInt(idx3)));
        }

        if (custom1 == null) {
            custom1 = new CustomAdapter(this, taskList);
            mTaskListView.setAdapter(custom1);
        } else {
            custom1.clear();
            custom1.addAll(taskList);
            custom1.notifyDataSetChanged();
        }


        cursor.close();
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskIDView = (TextView) parent.findViewById(R.id.idlist);
        String msg;
        String id = String.valueOf(taskIDView.getText());
        msg = datos.eliminarNota(id, idUser);
        makeToast(msg);
        updateUI(idUser);

    }

    public void editTask(View view)
    {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        final TextView taskIDView = (TextView) parent.findViewById(R.id.idlist);
        String task = String.valueOf(taskTextView.getText());


        final EditText taskEditText = new EditText(this);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Editar tarea")
                .setMessage("Algun cambio?")
                .setView(taskEditText)
                .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        String id = String.valueOf(taskIDView.getText());
                        Log.d(TAG, "CAMBIO: " + task + " ID " + id);

                        mensaje = datos.actualizarNotas(id, idUser, task);
                        updateUI(idUser);
                        makeToast(mensaje);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .create();
        dialog.show();

    }

    public void actualizarEstadoNota(View view)
    {
        View parent = (View) view.getParent();
        TextView taskIDView = (TextView) parent.findViewById(R.id.idlist);
        String id = String.valueOf(taskIDView.getText());

        mensaje = datos.actualizarEstado(id, idUser);
        makeToast(mensaje);
        updateUI(idUser);

    }


}
