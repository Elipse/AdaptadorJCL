/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ELIALVA
 */
public class BitacoraDeEjecucion {
    
    public static Map<String,List<String>> bitacora;
    
    static {
        bitacora = new HashMap<>();
    }
    
    public void registra(String member, List<String> mensajes) {
        bitacora.put(member, mensajes);
    }        
}
