/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import java.net.URL;

/**
 *
 * @author ELIALVA
 */
public class ComparaMembersModeloRespuesta {

    private BitacoraDeEjecucion bitacoraDeEjecucion;    
    private URL contenidoDestino;
    private URL contenidoOrigen;

    public BitacoraDeEjecucion getBitacoraDeEjecucion() {
        return bitacoraDeEjecucion;
    }

    public void setBitacoraDeEjecucion(BitacoraDeEjecucion bitacoraDeEjecucion) {
        this.bitacoraDeEjecucion = bitacoraDeEjecucion;
    }

    public URL getContenidoDestino() {
        return contenidoDestino;
    }

    public void setContenidoDestino(URL contenidoDestino) {
        this.contenidoDestino = contenidoDestino;
    }

    public URL getContenidoOrigen() {
        return contenidoOrigen;
    }

    public void setContenidoOrigen(URL contenidoOrigen) {
        this.contenidoOrigen = contenidoOrigen;
    }

    void agregaBitacoraDeEjecucion(BitacoraDeEjecucion bitacoraDeEjecucion) {
        this.bitacoraDeEjecucion = bitacoraDeEjecucion;
    }

    
    
    
    
}
