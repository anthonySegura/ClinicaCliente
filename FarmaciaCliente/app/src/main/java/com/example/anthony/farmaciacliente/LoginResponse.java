package com.example.anthony.farmaciacliente;

/**
 * Created by anthony on 24/11/16.
 */

public class LoginResponse {
    private int response;

    public LoginResponse(int response) {
        this.response = response;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
