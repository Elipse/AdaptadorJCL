/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c03.adaptadores;

import com.mycompany.c02.casosdeuso.CasoDeUsoAdaptador;
import com.mycompany.c02.casosdeuso.ModeloRespuesta;
import com.mycompany.c02.casosdeuso.Transferencia;
import java.util.List;

/**
 *
 * @author ELIALVA
 */
public class ControladorAdaptador {

    private final CasoDeUsoAdaptador casoDeUsoAdaptador;
    private final PresentadorAdaptador presentadorAdaptador;
    private final Vista vista;
    
    public ControladorAdaptador(CasoDeUsoAdaptador casoDeUsoAdaptador, 
                                PresentadorAdaptador presentadorAdaptador,
                                Vista vista) {
        this.casoDeUsoAdaptador = casoDeUsoAdaptador;
        this.presentadorAdaptador = presentadorAdaptador;
        this.vista = vista;        
    }
    
    public void resuelve(List<Transferencia> transferencias) {
        ModeloRespuesta mr = casoDeUsoAdaptador.promueveMembers(transferencias);
        
    }
    
    
    
}
