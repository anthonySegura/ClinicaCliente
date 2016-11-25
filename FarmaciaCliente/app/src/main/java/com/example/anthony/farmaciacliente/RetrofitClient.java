package com.example.anthony.farmaciacliente;

/**
 * Created by anthony on 24/11/16.
 */


import java.util.List;


import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


public interface RetrofitClient {

    static final String URL_BASE = "http://ec2-35-163-108-171.us-west-2.compute.amazonaws.com/";

    //Metodos GET

    @GET("Login")
    Call<LoginResponse> login(
            @Query("username") String username,
            @Query("passw") String passw
    );

    @GET("GetAllProducts")
    Call<List<Medicina>> cargarMedicinas();


    @GET("GetStatus")
    Call<StatusResponse> comprobarCambios();

    @GET("SetStatus")
    Call<Object> mandarCambio();

    //Metodos POST

    @FormUrlEncoded
    @POST("CreateClient")
    Call<UsuarioCliente> agregarCliente(
            @Field("nombre") String nombre,
            @Field("username") String username,
            @Field("telefono") String telefono,
            @Field("passw") String passw
    );

}
