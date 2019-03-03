/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c01.entidades;

import java.io.File;
import java.util.Iterator;

/**
 *
 * @author ELIALVA
 */
public class Member {

    private String biblioteca;     //DNCQP.BTCH.PROCLIB
    private String nombre;         //QAD2200
    private String servidor;       //INFODES

    private Iterator<String> contenido;

    public String getNombreAbsoluto() {
        //DNCQP.BTCH.PROCLIB(QAD2200)
        return biblioteca + "(" + nombre + ")";
    }

    public String getTrayectoria() {
        //DNCQP.BTCH.PROCLIB(QAD2200)@INFODES
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

    public Iterator<String> getContenido() {
        return contenido;
    }

    public void setContenido(Iterator<String> contenido) {
        this.contenido = contenido;
    }

}
