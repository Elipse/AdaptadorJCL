/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c03.adaptadores;

import com.mycompany.c02.casosdeuso.ComparaMembersModeloRespuesta;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ELIALVA
 */
public class ComparaMembersPresentador {

    public ComparaMembersModeloVista presenta(ComparaMembersModeloRespuesta modeloRespuesta) {
        URL contenidoOrigen = modeloRespuesta.getContenidoOrigen();
        URL contenidoDestino = modeloRespuesta.getContenidoDestino();
        try {
            File origen = Paths.get(contenidoOrigen.toURI()).toFile();
            File destino = Paths.get(contenidoDestino.toURI()).toFile();

            ComparaMembersModeloVista comparaMembersModeloVista = new ComparaMembersModeloVista();
            comparaMembersModeloVista.setFileOrigen(origen);
            comparaMembersModeloVista.setFileDestino(destino);

            return comparaMembersModeloVista;

        } catch (URISyntaxException ex) {
            Logger.getLogger(ComparaMembersPresentador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
