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
public class Transfer {

    private String server;
    private String remoteFile;
    private String localFile;

    public static Transfer newTransferencia() {
        return new Transfer();
    }

    private Transfer() {
    }

    public String getServer() {
        return server;
    }

    public Transfer setServer(String server) {
        this.server = server;
        return this;
    }

    public String getRemoteFile() {
        return remoteFile;
    }

    public Transfer setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
        return this;
    }

    public String getLocalFile() {
        return localFile;
    }

    public Transfer setLocalFile(String localFile) {
        this.localFile = localFile;
        return this;
    }

}
