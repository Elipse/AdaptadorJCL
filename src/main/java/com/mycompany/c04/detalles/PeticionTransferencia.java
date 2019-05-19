/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles;

import mx.com.eixy.swing.table.MyTableModel;

/**
 *
 * @author ELIALVA
 */
public class PeticionTransferencia {
    
    private String tipo;
    @MyTableModel.Ignore
    private String ambienteOrigen;
    private String memberOrigen;
    @MyTableModel.Ignore
    private String ambienteDestino;
    private String memberDestino;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   

    public String getMemberOrigen() {
        return memberOrigen;
    }

    public void setMemberOrigen(String memberOrigen) {
        this.memberOrigen = memberOrigen;
    }


    public String getMemberDestino() {
        return memberDestino;
    }

    public void setMemberDestino(String memberDestino) {
        this.memberDestino = memberDestino;
    }

    public String getAmbienteOrigen() {
        return ambienteOrigen;
    }

    public void setAmbienteOrigen(String ambienteOrigen) {
        this.ambienteOrigen = ambienteOrigen;
    }

    public String getAmbienteDestino() {
        return ambienteDestino;
    }

    public void setAmbienteDestino(String ambienteDestino) {
        this.ambienteDestino = ambienteDestino;
    }
    
}
