package com.petrociencia.jeremy.com.petrociencia;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.petrociencia.jeremy.com.petrociencia.modelo.Usuario;
import com.petrociencia.jeremy.com.petrociencia.sqlite.DBConsultas;

public class Registro extends AppCompatActivity {

    EditText user, pass;

    DBConsultas datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        datos = DBConsultas.obtenerInstancia(getApplicationContext());
        user = (EditText) findViewById(R.id.inputNewUser);
        pass = (EditText) findViewById(R.id.inputNewPassword);


    }

    public void registrar(View v)
    {
        String usuario = user.getText().toString();
        String contra = pass.getText().toString();
        boolean existente = false;

        if(!usuario.equals("") && !contra.equals(""))
        {
                int var;
                existente = datos.verificarExistente(usuario);

                if(!existente)
                {
                    var = datos.insertarUsuario(new Usuario("u",usuario, contra, "1"));
                    init();
                    makeToast(String.format("Se he creado un nuevo usuario %s", var));
                }
                else
                {
                    makeToast("El usuario ya existe");
                }

        }
        else
        {
            makeToast("Rellene los campos Usuario y Contraseña");
        }
    }
    public void salir(View view)
    {
       finish();
    }

    private void makeToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    private void init()
    {
        user.setText("");
        pass.setText("");
    }
}
