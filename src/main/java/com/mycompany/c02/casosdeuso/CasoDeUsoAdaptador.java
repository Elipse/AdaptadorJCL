/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c02.casosdeuso;

import com.mycompany.c01.entidades.Member;
import com.mycompany.c02.casosdeuso.Transferencia.Peticion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ELIALVA
 */
public class CasoDeUsoAdaptador {

    AlmacenMemberI almacenMember;
    FabricaEditorI fabricaEditor;

    public void promueveMembers(List<Transferencia> transferencias) {

        BitacoraDeEjecucion bitacoraDeEjecucion = new BitacoraDeEjecucion();
        List<Exception> lista = new ArrayList<>();

        transferencias.forEach((Transferencia transferencia) -> {

            Peticion origen = transferencia.getOrigen();

            Member origenMember = null;
            
            try {
                origenMember = almacenMember.buscaMemberViaID(origen);
            } catch (Exception ex) {
                lista.add(ex);
            }

            Editor editor = fabricaEditor.newEditor(creaIdEditor(transferencia));
            Iterator<String> conReemplazos = editor.reemplaza(origenMember.getContenido());
            Iterator<String> conAjustes = editor.ajusta(conReemplazos);

            Member destinoMember = generaMember(transferencia.getDestino());
            destinoMember.setContenido(conAjustes);

            String biblioteca = destinoMember.getBiblioteca();
            if (!almacenMember.verificaBiblioteca(biblioteca)) {
                almacenMember.creaBiblioteca(biblioteca);
            }

            almacenMember.promocionaMember(destinoMember);

        });
    }

    private static Member generaMember(Peticion peticion) {
        Member member = new Member();
        member.setBiblioteca(peticion.getBiblioteca());
        member.setNombre(peticion.getNombre());
        member.setServidor(peticion.getServidor());

        return member;
    }

    private static String creaIdEditor(Transferencia transferencia) {
        String tipo = transferencia.getTipo();
        String origen = transferencia.getOrigen().getServidor();
        String destino = transferencia.getDestino().getServidor();
        return origen + "-" + destino + "-" + tipo;
    }
}
