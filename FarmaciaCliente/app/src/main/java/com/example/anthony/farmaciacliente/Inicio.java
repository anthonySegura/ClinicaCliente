package com.example.anthony.farmaciacliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {

    ListView items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        items = (ListView)findViewById(R.id.listItems);
        getProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private myAdapter getAdapter(ArrayList<Medicina> datos){
        myAdapter adapter = new myAdapter(this, datos);
        return adapter;
    }


    private void getProducts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<List<Medicina>> call = retrofitClient.cargarMedicinas();

        call.enqueue(new Callback<List<Medicina>>() {
            @Override
            public void onResponse(Response<List<Medicina>> response, Retrofit retrofit) {

                List<Medicina> clientes_data = response.body();
                ArrayList<Medicina> data = new ArrayList<Medicina>();

                for (Medicina item : clientes_data){
                    data.add(item);
                }

                myAdapter adapter = getAdapter(data);
                items.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplication(), getString(R.string.Error_conexion), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


}
