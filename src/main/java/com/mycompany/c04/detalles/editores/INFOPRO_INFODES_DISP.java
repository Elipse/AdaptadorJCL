/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles.editores;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELIALVA
 */
@Component
public class INFOPRO_INFODES_DISP extends EditorEstandar {

    @Autowired
    protected Map<String, List<Reemplazo>> mapaDeReemplazos;

    @Override
    public Iterator<String> ajusta(Iterator<String> contenido) {
        return contenido;
    }

    @Override
    List<Reemplazo> obtieneReemplazos() {
        return mapaDeReemplazos.get(this.getClass().getSimpleName());
    }
}
