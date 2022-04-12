package com.josefsg.crud.dto;

public class Mensaje {
    private String mensaje;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public void executeMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
