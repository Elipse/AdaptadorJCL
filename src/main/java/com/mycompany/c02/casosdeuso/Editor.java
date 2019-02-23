/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import java.util.Iterator;

/**
 *
 * @author ELIALVA
 */
public abstract class Editor {
    
    
    public abstract Iterator<String> reemplaza(Iterator<String> contenido);
    
    public abstract Iterator<String>  ajusta(Iterator<String> contenido);
    
    
}
