/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.bitbyeteclub.c01.Sugerencia;
import mx.com.bitbyeteclub.c02.casosdeuso.BuscadorDeSugerenciasI;

/**
 *
 * @author ELIALVA
 */
public class Buscador implements BuscadorDeSugerenciasI {

    static final String TIPOS = "TIPOS";
    static final String AMBIENTE_ORIGEN = "AMBIENTE_ORIGEN";
    static final String BIBLIOTECA_ORIGEN = "BIBLIOTECA_ORIGEN";
    static final String AMBIENTE_DESTINO = "AMBIENTE_DESTINO";
    static final String BIBLIOTECA_DESTINO = "BIBLIOTECA_DESTINO";

    static final Properties PROPERTIES = new Properties();
    private static String[] ambientes;
    private static String[] tipos;
    private static String[] bibliotecas;

    static {
        try {
            InputStream resourceAsStream = Buscador.class
                    .getClassLoader()
                    .getResourceAsStream("autocompleta/ambientes.properties");
            PROPERTIES.load(resourceAsStream);
            ambientes = PROPERTIES.getProperty("ambientes").split("\\s*,\\s*");
            tipos = PROPERTIES.getProperty("tipos").split("\\s*,\\s*");
            bibliotecas = PROPERTIES.getProperty("bibliotecas").split("\\s*,\\s*");
        } catch (IOException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("user.dir = " + System.getProperty("user.dir"));
        System.out.println("user.home = " + System.getProperty("user.home"));
        System.out.println("resource loader1 = " + Buscador.class.getClassLoader().getResource("."));
        System.out.println("resource loader2 = " + Buscador.class.getResource("."));
    }

    @Override
    public List<Sugerencia> recuperaTextoEImagen(String ambito, String texto) {

        List<Sugerencia> listaSugerencias = new ArrayList<>();

        System.out.println("ambito " + ambito);
        System.out.println("texto " + texto);

        switch (ambito) {
            case TIPOS:
                return creaSugerencias(tipos, texto);
            case AMBIENTE_ORIGEN:
                return creaSugerencias(ambientes, texto);
            case BIBLIOTECA_ORIGEN:
                return creaSugerencias(bibliotecas, texto);
            case AMBIENTE_DESTINO:
                return creaSugerencias(ambientes, texto);
            case BIBLIOTECA_DESTINO:
                return creaSugerencias(bibliotecas, texto);
            default:
                break;
        }

        return listaSugerencias;
    }

    public static List<Sugerencia> creaSugerencias(String[] lista, String texto) {
        List<Sugerencia> sugerencias = new ArrayList<>();
        for (String sugerenciaStr : lista) {
            if (sugerenciaStr.contains(texto.trim().toUpperCase())) {
                Sugerencia s = new Sugerencia();
                s.setTexto(sugerenciaStr);
                sugerencias.add(s);
            }
        }
        return sugerencias;
    }

}
