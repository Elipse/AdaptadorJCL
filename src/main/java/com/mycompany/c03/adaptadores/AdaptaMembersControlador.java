/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c03.adaptadores;

import com.mycompany.c02.casosdeuso.AdaptaMembersCasoDeUso;
import com.mycompany.c02.casosdeuso.AdaptaMembersModeloRespuesta;
import com.mycompany.c02.casosdeuso.Transferencia;
import java.util.List;

/**
 *
 * @author ELIALVA
 */
public class AdaptaMembersControlador {

    private final AdaptaMembersCasoDeUso casoDeUso;
    private final AdaptaMembersPresentador presentador;
    private final Vista vista;

    public AdaptaMembersControlador(AdaptaMembersCasoDeUso casoDeUso,
            AdaptaMembersPresentador presentador,
            Vista vista) {
        this.casoDeUso = casoDeUso;
        this.presentador = presentador;
        this.vista = vista;
    }

    public void resuelve(List<Transferencia> transferencias) {
        AdaptaMembersModeloRespuesta modeloRespuesta = casoDeUso.promueveMembers(transferencias);
        AdaptaMembersModeloVista modeloVista = presentador.presenta(modeloRespuesta);
        vista.muestra(modeloVista);
    }
}
