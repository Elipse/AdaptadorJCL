/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import com.mycompany.c01.entidades.Member;
import java.net.URL;

/**
 *
 * @author ELIALVA
 */
public class ComparaMembersCasoDeUso {

    AlmacenMemberI almacenMember;

    public ComparaMembersCasoDeUso(AlmacenMemberI almacenMember) {
        this.almacenMember = almacenMember;

    }

    public ComparaMembersModeloRespuesta comparaMembers(Transferencia transferencia) {

        Member origen = transferencia.getOrigen();
        Member destino = transferencia.getDestino();

        ComparaMembersModeloRespuesta modeloRespuesta = new ComparaMembersModeloRespuesta();
        BitacoraDeEjecucion bitacoraDeEjecucion = new BitacoraDeEjecucion();

        URL contenidoOrigen = null;
        URL contenidoDestino = null;

        try {
            contenidoOrigen = almacenMember.obtieneURLDeContenido(origen.getTrayectoria());
            contenidoDestino = almacenMember.obtieneURLDeContenido(destino.getTrayectoria());
        } catch (ExcepcionComparacion e) {
            bitacoraDeEjecucion.registraExcepcion(transferencia, e);
        }

        modeloRespuesta.setContenidoOrigen(contenidoOrigen);
        modeloRespuesta.setContenidoDestino(contenidoDestino);
        modeloRespuesta.agregaBitacoraDeEjecucion(bitacoraDeEjecucion);
        
        return modeloRespuesta;
    }
}
