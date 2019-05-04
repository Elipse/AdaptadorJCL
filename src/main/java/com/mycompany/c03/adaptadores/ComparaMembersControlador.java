/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c03.adaptadores;

import com.mycompany.c02.casosdeuso.ComparaMembersCasoDeUso;
import com.mycompany.c02.casosdeuso.ComparaMembersModeloRespuesta;
import com.mycompany.c02.casosdeuso.Transferencia;

/**
 *
 * @author ELIALVA
 */
public class ComparaMembersControlador {

    private final ComparaMembersCasoDeUso casoDeUso;
    private final ComparaMembersPresentador presentador;
    private final Vista vista;

    public ComparaMembersControlador(ComparaMembersCasoDeUso casoDeUso,
            ComparaMembersPresentador presentador,
            Vista vista) {
        this.casoDeUso = casoDeUso;
        this.presentador = presentador;
        this.vista = vista;
    }

    public void compara(Transferencia transferencia) {
        ComparaMembersModeloRespuesta modeloRespuesta = casoDeUso.comparaMembers(transferencia);        
        ComparaMembersModeloVista modeloVista = presentador.presenta(modeloRespuesta);
        vista.muestra(modeloVista);
    }
}
