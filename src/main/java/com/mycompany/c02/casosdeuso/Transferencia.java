/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import com.mycompany.c01.entidades.Member;

/**
 *
 * @author ELIALVA
 */
public class Transferencia {

    private String tipo;
    private Member origen;
    private Member destino;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Member getOrigen() {
        return origen;
    }

    public void setOrigen(Member origen) {
        this.origen = origen;
    }

    public Member getDestino() {
        return destino;
    }

    public void setDestino(Member destino) {
        this.destino = destino;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Transferencia)) {
            return false;
        }
        Transferencia transferencia = (Transferencia) obj;
        String id1 = transferencia.getOrigen().getTrayectoria() + transferencia.getDestino().getTrayectoria();
        String id2 = this.getOrigen().getTrayectoria() + this.getDestino().getTrayectoria();

        return id1.equals(id2);
    }

    @Override
    public int hashCode() {
        String id1 = this.getOrigen().getTrayectoria() + this.getDestino().getTrayectoria();
        return id1.hashCode();
    }
}
