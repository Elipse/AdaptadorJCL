/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.eixy.utilities.zos.ftp;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author ELIALVA
 */
public class FTPClientFactory {

    private final List<FTPServer> servers;

    private final Map<String, FTPClient> connectionList;

    private FTPClient ftpClient;

    public FTPClientFactory(List<FTPServer> servers) {
        this.servers = servers;
        this.connectionList = new HashMap<>();
    }

    public File getFile(Transferencia transferencia) throws IOException {

        String server = transferencia.getServer();
        ftpClient = connectionList.get(server);
        String remoteFile = "'" + transferencia.getRemoteFile() + "'";
        String localFile = transferencia.getLocalFile();

        Path path = new File(localFile).toPath();
        try (OutputStream localOutputStream = Files.newOutputStream(path, StandardOpenOption.CREATE)) {
            boolean rc = ftpClient.retrieveFile(remoteFile, localOutputStream);

            if (rc == false) {
                throw new IOException(ftpClient.getReplyString());
            }

            return path.toFile();
        }
    }

    public void putFile(Transferencia transferencia) {

    }
}
