/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles;

import com.mycompany.c01.entidades.Member;
import com.mycompany.c02.casosdeuso.AlmacenMemberI;
import com.mycompany.c02.casosdeuso.ExcepcionDescarga;
import com.mycompany.c02.casosdeuso.PeticionMember;
import com.mycompany.c02.casosdeuso.Transferencia;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.eixy.utilities.zos.ftp.DataSetDefinition;
import mx.com.eixy.utilities.zos.ftp.FTPClientFactory;
import mx.com.eixy.utilities.zos.ftp.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELIALVA
 */
@Component
public class AlmacenMember implements AlmacenMemberI {

    @Value("#{'${directorio_temporal}' + '\\'}")
    private String directorioTemporal;

    @Autowired
    private FTPClientFactory ftpClientFactory;

    private String servidor;
    private String biblioteca;
    private String nombre;
    private String memberRemoto;
    private String memberLocal;
    private Transfer transferencia;
    private File file;
    private Iterator<String> contenido;

    private final Map<String, URL> contenidos;

    public AlmacenMember() {
        contenidos = new HashMap<>();
    }

    @Override
    public Member buscaMemberViaID(PeticionMember peticion) throws ExcepcionDescarga {

        try {
            servidor = peticion.getServidor();
            biblioteca = peticion.getBiblioteca();
            nombre = peticion.getNombre();
            memberRemoto = biblioteca + "(" + nombre + ")";
            memberLocal = directorioTemporal + nombre;

            transferencia = creaTransferencia();
            file = ftpClientFactory.getDataSet(transferencia);
            contenido = Files.readAllLines(file.toPath()).iterator();

            guardaContenido(peticion);

            return creaMember();
        } catch (IOException ex) {
            throw new ExcepcionDescarga("buscaMemberViaID", ex);
        }
    }

    private void guardaContenido(PeticionMember peticion) throws MalformedURLException {
        String id = peticion.getNombreAbsoluto(); //o getTrayectoria???
        URL url = file.toURI().toURL();
        contenidos.put(id, url);
    }

    private Transfer creaTransferencia() {
        return Transfer.newTransferencia().
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
    public boolean verificaBiblioteca(Member biblioteca) {
        try {
            return ftpClientFactory.isRemoteDataSetAvailable(biblioteca.getServidor(), biblioteca.getBiblioteca());
        } catch (IOException ex) {
            Logger.getLogger(AlmacenMember.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void creaBiblioteca(Member biblioteca) {

        DataSetDefinition dataSetDefinition = DataSetDefinition.newDataDefinition()
                .setDsname(biblioteca.getBiblioteca())
                .setDirectorySize("di=900")
                .setRecordFormat("rec=fb")
                .setRecordLength("lr=080")
                .setBlkSize("blksize=32720")
                .setSpaceUnit("cy")
                .setPrimarySpace("pri=10")
                .setSecondarySpace("sec=5");
        try {
            ftpClientFactory.createPartitionedDataSet(biblioteca.getServidor(), dataSetDefinition);
        } catch (IOException ex) {
            Logger.getLogger(AlmacenMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void promocionaMember(Transferencia transferencia) {

        URL get = contenidos.get(transferencia.getOrigen().getNombreAbsoluto());
        Path path = Paths.get("");
        
        try {
           path =  Paths.get(get.toURI()).toAbsolutePath();
        } catch (URISyntaxException ex) {
            Logger.getLogger(AlmacenMember.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transfer setLocalFile = Transfer.newTransferencia().
                setServer(transferencia.getDestino().getServidor()).
                setRemoteFile(transferencia.getDestino().getNombreAbsoluto()).
                setLocalFile(path.toString());

        try {
            ftpClientFactory.putMember(setLocalFile);
        } catch (IOException ex) {
            Logger.getLogger(AlmacenMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void guardaContenido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
