/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles;

import com.mycompany.c01.entidades.Member;
import com.mycompany.c02.casosdeuso.AlmacenMemberI;
import com.mycompany.c02.casosdeuso.ExcepcionCarga;
import com.mycompany.c02.casosdeuso.ExcepcionDescarga;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mx.com.eixy.utilities.zos.ftp.DataSetDefinition;
import mx.com.eixy.utilities.zos.ftp.FTPClientFactory;
import mx.com.eixy.utilities.zos.ftp.Transfer;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELIALVA
 */
@Component
public class AlmacenMember implements AlmacenMemberI {

    @Value("#{'${directorio.origen}' + '\\'}")
    private String directorioOrigen;
    @Value("#{'${directorio.destino}' + '\\'}")
    private String directorioDestino;
    @Autowired
    private FTPClientFactory ftpClientFactory;
    @Autowired
    private DataSetDefinition dataSetDefinition;

    private final Map<String, URL> contenidos = new HashMap<>();

    @Override
    public Member recuperaMember(Member peticion) throws ExcepcionDescarga {

        try {
            Transfer transferencia = creaTransferencia(peticion);

            File file = ftpClientFactory.getDataSet(transferencia);
            guardaContenido(peticion, file);

            Iterator<String> contenido = Files.readAllLines(file.toPath()).iterator();

            return creaMember(peticion, contenido);
        } catch (IOException ex) {
            throw new ExcepcionDescarga(ex);
        }
    }

    private Transfer creaTransferencia(Member peticion) {
        return Transfer.newTransferencia().
                setServer(peticion.getServidor()).
                setRemoteFile(peticion.getNombreAbsoluto()).
                setLocalFile(directorioOrigen + peticion.getNombre());
    }

    private Member creaMember(Member peticion, Iterator<String> contenido) {
        Member member = new Member();
        member.setServidor(peticion.getServidor());
        member.setBiblioteca(peticion.getBiblioteca());
        member.setNombre(peticion.getNombre());
        member.setContenido(contenido);
        return member;
    }

    @Override
    public boolean verificaBiblioteca(Member member) throws ExcepcionCarga {

        String ftpServer = member.getServidor();
        String remoteDataSet = member.getBiblioteca();

        try {
            return ftpClientFactory.isRemoteDataSetAvailable(ftpServer, remoteDataSet);
        } catch (IOException e) {
            throw new ExcepcionCarga(e);
        }
    }

    @Override
    public void creaBiblioteca(Member member) throws ExcepcionCarga {

        String ftpServer = member.getServidor();
        String remoteDataSet = member.getBiblioteca();

        dataSetDefinition.setDsname(remoteDataSet);

        try {
            ftpClientFactory.createPartitionedDataSet(ftpServer, dataSetDefinition);
        } catch (IOException e) {
            throw new ExcepcionCarga(e);
        }
    }

    @Override
    public void promocionaMember(Member member) throws ExcepcionCarga {

        File archivoLocal = new File(directorioDestino + member.getNombre());

        List<String> contenidoDestino = new ArrayList<>();
        member.getContenido().forEachRemaining(contenidoDestino::add);

        try {
            FileUtils.writeLines(archivoLocal, contenidoDestino);

            Transfer transfer = Transfer.newTransferencia().
                    setServer(member.getServidor()).
                    setRemoteFile(member.getNombreAbsoluto()).
                    setLocalFile(archivoLocal.getAbsolutePath());
            ftpClientFactory.putMember(transfer);
            File file = archivoLocal;
            guardaContenido(member, file);

        } catch (IOException e) {
            throw new ExcepcionCarga(e);
        }
    }

    private void guardaContenido(Member member, File file) throws MalformedURLException {
        URL url = file.toURI().toURL();
        contenidos.put(member.getTrayectoria(), url);
    }

    @Override
    public URL obtieneURLDeContenido(String id) {
        return contenidos.get(id);
    }

}
