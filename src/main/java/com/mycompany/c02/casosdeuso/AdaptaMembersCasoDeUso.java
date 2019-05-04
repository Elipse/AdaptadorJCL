package com.mycompany.c02.casosdeuso;

import com.mycompany.c01.entidades.Member;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ELIALVA
 */
public class AdaptaMembersCasoDeUso {

    AlmacenMemberI almacenMember;
    EditorFabrica fabricaEditor;

    public AdaptaMembersCasoDeUso(AlmacenMemberI almacenMember, EditorFabrica fabricaEditor) {
        this.almacenMember = almacenMember;
        this.fabricaEditor = fabricaEditor;
    }

    public AdaptaMembersModeloRespuesta promueveMembers(List<Transferencia> transferencias) {

        BitacoraDeEjecucion bitacoraDeEjecucion = new BitacoraDeEjecucion();
        AdaptaMembersModeloRespuesta modeloRespuesta = new AdaptaMembersModeloRespuesta();
        modeloRespuesta.setBitacora(bitacoraDeEjecucion);        
        
        transferencias.forEach((Transferencia transferencia) -> {
            
            try {
                Member peticionOrigen = transferencia.getOrigen();               
                
                Member memberOrigen = almacenMember.recuperaMember(peticionOrigen);
                Member memberDestino = transferencia.getDestino();
               
                Editor editor = fabricaEditor.newEditor(creaIdEditor(transferencia));
                
                Iterator<String> contenidoOriginal = memberOrigen.getContenido();
                Iterator<String> conReemplazos = editor.reemplaza(contenidoOriginal);
                Iterator<String> conAjustes = editor.ajusta(conReemplazos);
                
                memberDestino.setContenido(conAjustes);

                if (!almacenMember.verificaBiblioteca(memberDestino)) {
                    almacenMember.creaBiblioteca(memberDestino);
                }

                almacenMember.promocionaMember(memberDestino);
            } catch (ExcepcionCarga | ExcepcionDescarga e) {
                bitacoraDeEjecucion.registraExcepcion(transferencia, e);                
            }
        });

        return modeloRespuesta;
    }

    private static String creaIdEditor(Transferencia transferencia) {
        String tipo = transferencia.getTipo();
        String origen = transferencia.getOrigen().getServidor();
        String destino = transferencia.getDestino().getServidor();
        return origen + "_" + destino + "_" + tipo;
    }
}
