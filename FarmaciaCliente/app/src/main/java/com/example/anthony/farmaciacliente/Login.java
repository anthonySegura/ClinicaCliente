package com.example.anthony.farmaciacliente;

import android.content.Intent;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Login extends AppCompatActivity {

    TextView username;
    TextView passw;
    Button ingresar;
    Button registro;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.MensajeCarga));
        pDialog.setCancelable(false);
        username = (TextView)findViewById(R.id.loginNombreUsuario);
        passw = (TextView)findViewById(R.id.loginPassw);
        ingresar = (Button)findViewById(R.id.loginBtn);
        registro = (Button)findViewById(R.id.loginRegBtn);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = username.getText().toString();
                String p = passw.getText().toString();
                comprobarUsuario(u, p);
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);
            }
        });
    }



    private void comprobarUsuario(String username, String passw){
        pDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<LoginResponse> call = retrofitClient.login(username, passw);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                LoginResponse _response = response.body();
                pDialog.hide();
                if(_response.getResponse() == 1){
                    Intent intent = new Intent(getApplicationContext(), Inicio.class);
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.Datos_incorrectos), Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                pDialog.hide();
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.Error_conexion), Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}
