/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c03.adaptadores;

import java.io.File;

/**
 *
 * @author ELIALVA
 */
public class ComparaMembersModeloVista {

    public File getOrigen() {
        return origen;
    }

    public File getDestino() {
        return destino;
    }

    private File origen;
    private File destino;

    void setFileOrigen(File origen) {
        this.origen = origen;
    }

    void setFileDestino(File destino) {
        this.destino = destino;
    }
    
}
