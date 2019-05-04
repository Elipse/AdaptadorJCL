/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ELIALVA
 */
public class BitacoraDeEjecucion {

    private static final Map<Transferencia, Exception> MAPA_DE_EXCEPCIONES;

    static {
        MAPA_DE_EXCEPCIONES = new HashMap<>();
    }

    void registraExcepcion(Transferencia transferencia, Exception e) {
        MAPA_DE_EXCEPCIONES.put(transferencia, e);
    }

    public Map<Transferencia, Exception> obtieneExcepciones() {
        return MAPA_DE_EXCEPCIONES;
    }

}
