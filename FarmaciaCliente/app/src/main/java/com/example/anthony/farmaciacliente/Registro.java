package com.example.anthony.farmaciacliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class Registro extends AppCompatActivity {

    EditText nombre;
    EditText telefono;
    EditText username;
    EditText passw;
    Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = (EditText)findViewById(R.id.registroNombre);
        telefono = (EditText)findViewById(R.id.registroTelefono);
        username = (EditText)findViewById(R.id.registroNombreUsuario);
        passw = (EditText)findViewById(R.id.registroPassw);
        btnAceptar = (Button)findViewById(R.id.btnRegistro);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarCampos();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_registro,menu);
        return true;
    }


    private void comprobarCampos() {

        String n = nombre.getText().toString();
        String t = telefono.getText().toString();
        String u = username.getText().toString();
        String p = passw.getText().toString();

        if(n.equals("") || t.equals("") || u.equals("") || p.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.Faltan_Datos), Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            registrarUsuario(n, u, t, p);
        }

    }

    private void registrarUsuario(String n, String u, String t, String p) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<UsuarioCliente> call = retrofitClient.agregarCliente(n, u,t,p);

        call.enqueue(new Callback<UsuarioCliente>() {
            @Override
            public void onResponse(Response<UsuarioCliente> response, Retrofit retrofit) {
                System.out.println("OK");
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplication(), getString(R.string.Error_conexion), Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.regCancelar){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
