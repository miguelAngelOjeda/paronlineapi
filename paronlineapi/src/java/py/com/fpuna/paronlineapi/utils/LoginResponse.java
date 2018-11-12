/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.fpuna.paronlineapi.utils;

import java.util.List;
import py.com.fpuna.paronlineapi.entity.Cliente;


/**
 *
 * @author mojeda
 */
public class LoginResponse {
    
    private boolean error;
    
    private String mensaje;
    
    private Cliente usuario;

    /**
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the usuario
     */
    public Cliente getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    
}
