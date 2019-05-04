/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c03.adaptadores;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ELIALVA
 */
public class AdaptaMembersModeloVista {

    private Map<String, List<String>> bitacora;

    public Map<String, List<String>> getBitacora() {
        return bitacora;
    }

    void setBitacora(Map<String, List<String>> bitacora) {
        this.bitacora = bitacora;
    }

}
