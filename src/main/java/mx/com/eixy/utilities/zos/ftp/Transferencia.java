/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.eixy.utilities.zos.ftp;

/**
 *
 * @author ELIALVA
 */
public class Transferencia {

    private String server;
    private String remoteFile;
    private String localFile;

    public static Transferencia newTransferencia() {
        return new Transferencia();
    }

    private Transferencia() {
    }

    public String getServer() {
        return server;
    }

    public Transferencia setServer(String server) {
        this.server = server;
        return this;
    }

    public String getRemoteFile() {
        return remoteFile;
    }

    public Transferencia setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
        return this;
    }

    public String getLocalFile() {
        return localFile;
    }

    public Transferencia setLocalFile(String localFile) {
        this.localFile = localFile;
        return this;
    }

}
