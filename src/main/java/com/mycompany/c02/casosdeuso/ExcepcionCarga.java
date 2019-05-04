/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

/**
 *
 * @author ELIALVA
 */
public class ExcepcionCarga extends Exception {

    public ExcepcionCarga() {
        super();
    }

    public ExcepcionCarga(String message) {
        super(message);
    }

    public ExcepcionCarga(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcionCarga(Throwable cause) {
        super(cause);
    }

    protected ExcepcionCarga(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
