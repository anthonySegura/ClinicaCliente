package com.example.anthony.farmaciacliente;

/**
 * Created by anthony on 24/11/16.
 */

public class StatusResponse {

    private Boolean status;

    public StatusResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
