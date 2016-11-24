package com.example.anthony.farmaciacliente;

/**
 * Created by anthony on 24/11/16.
 */

public class UsuarioCliente {

    private String nombre;
    private String username;
    private String telefono;
    private String passw;

    public UsuarioCliente(String nombre, String username, String passw, String telefono) {
        this.nombre = nombre;
        this.username = username;
        this.passw = passw;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
