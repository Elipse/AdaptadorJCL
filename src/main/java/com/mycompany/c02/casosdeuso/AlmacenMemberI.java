/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import com.mycompany.c01.entidades.Member;
import com.mycompany.c02.casosdeuso.Transferencia.Peticion;
import java.net.URL;

/**
 *
 * @author ELIALVA
 */
public interface AlmacenMemberI {
    
    Member buscaMemberViaID(Peticion peticion) throws ExcepcionDescarga;
    
    void guardaContenido();
    
    URL obtieneURLDeContenido(String id);

    boolean verificaBiblioteca(String biblioteca);
    
    boolean creaBiblioteca(String biblioteca);
    
    void promocionaMember(Member destinoMember);
    
}
