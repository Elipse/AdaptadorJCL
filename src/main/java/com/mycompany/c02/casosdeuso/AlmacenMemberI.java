/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import com.mycompany.c01.entidades.Member;
import java.net.URL;

/**
 *
 * @author ELIALVA
 */
public interface AlmacenMemberI {
    
    Member recuperaMember(Member peticion) throws ExcepcionDescarga;
    
    boolean verificaBiblioteca(Member biblioteca) throws ExcepcionCarga;
    
    void creaBiblioteca(Member biblioteca) throws ExcepcionCarga;
    
    void promocionaMember(Member member) throws ExcepcionCarga;
    
    URL obtieneURLDeContenido(String id) throws ExcepcionComparacion;
    
}
