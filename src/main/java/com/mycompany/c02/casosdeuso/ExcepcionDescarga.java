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
public class ExcepcionDescarga extends Exception {

    public ExcepcionDescarga() {
        super();
    }

    public ExcepcionDescarga(String message) {
        super(message);
    }

    public ExcepcionDescarga(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcionDescarga(Throwable cause) {
        super(cause);
    }

    protected ExcepcionDescarga(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
