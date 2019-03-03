/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles.editores;

import com.mycompany.c02.casosdeuso.Editor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ELIALVA
 */
public abstract class EditorEstandar implements Editor {

    @Override
    public Iterator<String> reemplaza(Iterator<String> contenido) {

        List<String> contenidoReemplazado = new ArrayList<>();

        List<Reemplazo> reemplazos = obtieneReemplazos();

        contenido.forEachRemaining(lineaDeCodigo -> {
            reemplazos.forEach(reemplazo -> {

                String cadenaDeBusqueda = reemplazo.getCadenaDeBusqueda();
                String cadenaDeReemplazo = reemplazo.getCadenaDeReemplazo();

                String tmp = lineaDeCodigo.replaceAll(cadenaDeBusqueda, cadenaDeReemplazo);
                contenidoReemplazado.add(tmp);
            });
        });

        return contenidoReemplazado.iterator();
    }

    abstract List<Reemplazo> obtieneReemplazos();

    @Override
    public abstract Iterator<String> ajusta(Iterator<String> contenido);

}
