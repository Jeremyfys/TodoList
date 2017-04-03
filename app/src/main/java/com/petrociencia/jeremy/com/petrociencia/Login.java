package com.petrociencia.jeremy.com.petrociencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import com.petrociencia.jeremy.com.petrociencia.sqlite.DBConsultas;
import com.petrociencia.jeremy.com.petrociencia.R;
import com.petrociencia.jeremy.com.petrociencia.modelo.Usuario;
import com.petrociencia.jeremy.com.petrociencia.modelo.Notas;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.petrociencia.jeremy.com.petrociencia.sqlite.DBConsultas;


public class Login extends AppCompatActivity {

    //private LoginButton loginButton;
    //private CallbackManager callbackManager;
    private EditText User, Password;
    DBConsultas datos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        datos = DBConsultas.obtenerInstancia(getApplicationContext());
        User = (EditText) findViewById(R.id.inputUsername);
        Password = (EditText) findViewById(R.id.inputPassword);
    }

    public void ingresar(View view)
    {

        String usuario = User.getText().toString();
        String password = Password.getText().toString();
        Log.d("LOG revisar", String.format("Valores %s ,  %s", usuario, password));
        Usuario v ;

        v = datos.verificarUsuario(usuario, password);

        //Log.d("FIELDS", String.format("VALORES LOG %s %s %s %s", v.idUsuario, v.nombreUsuario, v.contrasena, v.tipo));

        if(v.tipo.equals("0"))
        {
            Intent ven = new Intent(this,Registro.class);
            startActivity(ven);
        }
        else if(v.tipo.equals("1"))
        {
            Intent ven = new Intent(this,Lista.class);
            ven.putExtra("nombre", usuario);
            ven.putExtra("id", v.idUsuario);
            startActivity(ven);
        }
        else
        {
            makeToast("El Usuario y/o Contraseña es incorrecto");
        }
    }
    public void salir(View view)
    {
        finish();
    }

    private void makeToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

        /*callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String message = "Success";
                makeToast(message);
            }

            @Override
            public void onCancel() {
                String message = "Cancel";
                makeToast(message);
            }

            @Override
            public void onError(FacebookException error) {
                String errorMessage = "Login error.";
                makeToast(errorMessage);
            }
        });

        if(isLoggedIn())
        {
            String token = "User ID: " + AccessToken.getCurrentAccessToken().getUserId();
        }



    }
    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return (accessToken != null) && (!accessToken.isExpired());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * creamos los toast
     * @param text



    @Override
    protected void onResume() {
        super.onResume();

        //los logs de 'instalar' y 'aplicación activa' App Eventos.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs de'app desactivada' App Eventos.
        AppEventsLogger.deactivateApp(this);
    }*/


}
