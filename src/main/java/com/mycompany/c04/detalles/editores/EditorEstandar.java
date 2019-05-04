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
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ELIALVA
 */
public abstract class EditorEstandar implements Editor {

    @Autowired
    protected Map<String, List<Reemplazo>> mapaDeReemplazos;

    @Override
    public Iterator<String> reemplaza(Iterator<String> contenido) {

        List<String> contenidoReemplazado = new ArrayList<>();

        List<Reemplazo> reemplazos =  mapaDeReemplazos.get(this.getClass().getSimpleName());

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

    @Override
    public abstract Iterator<String> ajusta(Iterator<String> contenido);

}
