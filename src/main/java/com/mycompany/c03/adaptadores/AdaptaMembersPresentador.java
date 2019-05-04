/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c03.adaptadores;

import com.mycompany.c02.casosdeuso.AdaptaMembersModeloRespuesta;
import com.mycompany.c02.casosdeuso.BitacoraDeEjecucion;
import com.mycompany.c02.casosdeuso.ExcepcionDescarga;
import com.mycompany.c02.casosdeuso.Transferencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ELIALVA
 */
public class AdaptaMembersPresentador {

    AdaptaMembersModeloVista presenta(AdaptaMembersModeloRespuesta modeloRespuesta) {
        BitacoraDeEjecucion bitacora = modeloRespuesta.getBitacora();

        Map<Transferencia, Exception> excepciones = bitacora.obtieneExcepciones();
        
        Map<String, List<String>> textoBitacora = new HashMap<>();
        

        excepciones.forEach((transferencia, excepcion) -> {
            System.out.println("transferencia  "
                    + transferencia.getOrigen().getTrayectoria()
                    + " - "
                    + transferencia.getDestino().getTrayectoria());
            System.out.println("excepcion " + excepcion.getMessage());
            
            String trayectoriaOrigen = transferencia.getOrigen().getTrayectoria();
            String trayectoriaDestino = transferencia.getDestino().getTrayectoria();
            List<String> texto = new ArrayList<>();
            
            if (excepcion instanceof ExcepcionDescarga) {
                texto.add(trayectoriaOrigen);
            }
            
            texto.add(excepcion.getMessage());
            
            textoBitacora.put(trayectoriaOrigen, texto);
            
            
            
        });

        AdaptaMembersModeloVista adaptaMembersModeloVista = new AdaptaMembersModeloVista();
        adaptaMembersModeloVista.setBitacora(textoBitacora);
        return adaptaMembersModeloVista;
    }

}
