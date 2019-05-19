/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles.editores;

import com.mycompany.c04.detalles.editor.EditorEstandar;
import java.util.Iterator;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELIALVA
 */
@Component
public class DES_PR1_PROCS extends EditorEstandar {

   

    @Override
    public Iterator<String> ajusta(Iterator<String> contenido) {
        return contenido;
    }

    
}
