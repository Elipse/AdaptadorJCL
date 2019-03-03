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
    
    Member buscaMemberViaID(PeticionMember peticion) throws ExcepcionDescarga;
    
    void guardaContenido();
    
    URL obtieneURLDeContenido(String id);

    boolean verificaBiblioteca(Member biblioteca);
    
    void creaBiblioteca(Member biblioteca);
    
    void promocionaMember(Transferencia transferencia);
    
}
