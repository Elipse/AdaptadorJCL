/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

/**
 *
 * @author ELIALVA
 */
public class Transferencia {

    private String tipo;
    private PeticionMember origen;
    private PeticionMember destino;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public PeticionMember getOrigen() {
        return origen;
    }

    public void setOrigen(PeticionMember origen) {
        this.origen = origen;
    }

    public PeticionMember getDestino() {
        return destino;
    }

    public void setDestino(PeticionMember destino) {
        this.destino = destino;
    }

   

}
