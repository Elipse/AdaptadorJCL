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
    private Peticion origen;
    private Peticion destino;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Peticion getOrigen() {
        return origen;
    }

    public void setOrigen(Peticion origen) {
        this.origen = origen;
    }

    public Peticion getDestino() {
        return destino;
    }

    public void setDestino(Peticion destino) {
        this.destino = destino;
    }

    public static class Peticion {

        private String biblioteca;
        private String nombre;
        private String servidor;

        public String getId() {
            return biblioteca + "(" + nombre + ")" + "@" + servidor;
        }

        public String getBiblioteca() {
            return biblioteca;
        }

        public void setBiblioteca(String biblioteca) {
            this.biblioteca = biblioteca;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getServidor() {
            return servidor;
        }

        public void setServidor(String servidor) {
            this.servidor = servidor;
        }

    }

}
