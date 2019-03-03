/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles;

import com.mycompany.c02.casosdeuso.Editor;
import com.mycompany.c02.casosdeuso.FabricaEditorI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELIALVA
 */
@Component
public class FabricaDeEditores implements FabricaEditorI{

    @Autowired
    Map<String,Editor> mapaDeEditores;
    
    @Override
    public Editor newEditor(String nombreDelEditor) {
        Editor editor = mapaDeEditores.get(nombreDelEditor);
        return editor;
    }
    
}
