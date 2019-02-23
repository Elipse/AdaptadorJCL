/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles;

import com.mycompany.c01.entidades.Member;
import com.mycompany.c02.casosdeuso.AlmacenMemberI;
import com.mycompany.c02.casosdeuso.ExcepcionDescarga;
import static com.mycompany.c02.casosdeuso.Transferencia.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import mx.com.eixy.utilities.zos.ftp.FTPClientFactory;
import mx.com.eixy.utilities.zos.ftp.FTPServer;
import mx.com.eixy.utilities.zos.ftp.Transferencia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELIALVA
 */
@Component
public class AlmacenMember implements AlmacenMemberI {

    @Resource(name = "listaDeServidoresFTP")
    public List<FTPServer> servers;

    @Value("#{'${directorio_temporal}' + '\\'}")
    private String directorioTemporal;
    private final FTPClientFactory ftpClientFactory;
    private String servidor;
    private String biblioteca;
    private String nombre;
    private String memberRemoto;
    private String memberLocal;
    private Transferencia transferencia;
    private File file;
    private Iterator<String> contenido;
    
    private final Map<String,URL> contenidos;

    public AlmacenMember() {
        ftpClientFactory = new FTPClientFactory(servers);
        contenidos = new HashMap<>();
    }

    @Override
    public Member buscaMemberViaID(Peticion peticion) throws ExcepcionDescarga {

        try {
            servidor = peticion.getServidor();
            biblioteca = peticion.getBiblioteca();
            nombre = peticion.getNombre();
            memberRemoto = biblioteca + "(" + nombre + ")";
            memberLocal = directorioTemporal + nombre;

            transferencia = creaTransferencia();
            file = ftpClientFactory.getFile(transferencia);
            contenido = Files.readAllLines(file.toPath()).iterator();
            
            guardaContenido();

            return creaMember();
        } catch (IOException ex) {
            throw new ExcepcionDescarga("buscaMemberViaID", ex);
        }
    }
    
    private void guardaContenido(Peticion peticion) throws MalformedURLException {
        String id = peticion.getId();
        URL url = file.toURI().toURL();
        contenidos.put(id, url);
        
    }

    private Transferencia creaTransferencia() {
        return Transferencia.newTransferencia().
                setServer(servidor).
                setRemoteFile(memberRemoto).
                setLocalFile(memberLocal);
    }

    private Member creaMember() {
        Member member = new Member();
        member.setServidor(servidor);
        member.setBiblioteca(biblioteca);
        member.setNombre(nombre);
        member.setContenido(contenido);
        
        return member;
    }

    @Override
    public URL obtieneURLDeContenido(String id) {
        return contenidos.get(id);
    }

    @Override
    public void guardaContenido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificaBiblioteca(String biblioteca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean creaBiblioteca(String biblioteca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void promocionaMember(Member destinoMember) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
