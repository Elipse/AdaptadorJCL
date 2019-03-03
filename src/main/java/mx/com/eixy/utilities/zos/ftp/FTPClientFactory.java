/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.eixy.utilities.zos.ftp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author ELIALVA
 */
public class FTPClientFactory {

    private final Map<String, FTPServer> ftpServersMap;
    private final Map<String, FTPClient> ftpClientsMap;
    private final List<FTPServer> servers;

    public FTPClientFactory(List<FTPServer> servers) {
        this.servers = servers;
        this.ftpServersMap = new HashMap<>();
        this.ftpClientsMap = new HashMap<>();
        initMaps();
    }

    private void initMaps() {
        servers.forEach((FTPServer ftpServer) -> {
            String ftpServerName = ftpServer.getServerName();
            FTPClient ftpClient = createAsciiFTPClient(ftpServer);
            this.ftpServersMap.put(ftpServerName, ftpServer);
            this.ftpClientsMap.put(ftpServerName, ftpClient);
        });
    }

    /**
     * It retrieves both member of pds and sequential file.
     *
     * @param transferencia
     * @return
     * @throws IOException
     */
    public File getDataSet(Transfer transferencia) throws IOException {

        //ERRORES
        //NO PUEDE GRABAR EN C
        String server = transferencia.getServer();
        FTPClient ftpClient = findFTPClient(server);
        String remoteFile = "'" + transferencia.getRemoteFile() + "'";
        String localFile = transferencia.getLocalFile();

        Path path = new File(localFile).toPath();
        try (OutputStream localOutputStream = Files.newOutputStream(path, StandardOpenOption.CREATE)) {

            if (ftpClient.retrieveFile(remoteFile, localOutputStream)) {
                return path.toFile();
            }

            throw new IOException(ftpClient.getReplyString());
        }
    }

    /**
     * It uploads both member of pds and sequential file.<br> *
     *
     * @param transferencia
     * @throws IOException
     */
    public void putMember(Transfer transferencia) throws IOException {

        //ERRORES        
        //ARCHIVO LOCAL NO EXISTE
        String server = transferencia.getServer();
        FTPClient ftpClient = findFTPClient(server);
        String remoteFile = "'" + transferencia.getRemoteFile() + "'";
        String localFile = transferencia.getLocalFile();

        Path path = new File(localFile).toPath();
        try (InputStream localInputStream = Files.newInputStream(path, StandardOpenOption.READ)) {
            //SI EL PDS EXISTE CREA EL MEMBER TRUNCADO ---ESTA OK
            //SI EL PDS NO EXISTE MARCA EXCEPTION  --- ESTA OK. LA VERIF & CREACION DE BIB ES OTRO CASO
            if (ftpClient.storeFile(remoteFile, localInputStream)) {
                return;
            }
            throw new IOException(ftpClient.getReplyString());
        } catch (IOException e) {
            throw e;
        }
    }

    public boolean isRemoteDataSetAvailable(String ftpServerName, String remoteFile) throws IOException {

        FTPClient ftpClient = findFTPClient(ftpServerName);
        String tmpRemoteFile = "'" + remoteFile + "'";

        try {
            ftpClient.listNames(tmpRemoteFile);
            return FTPReply.isPositiveCompletion(ftpClient.getReplyCode());
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * It deletes and creates a PDS.
     *
     * @param ftpServerName
     * @param dataDefinition
     * @return
     * @throws IOException
     */
    public void createPartitionedDataSet(String ftpServerName, DataSetDefinition dataDefinition) throws IOException {

        FTPClient ftpClient = findFTPClient(ftpServerName);

        String dataSetName = "'" + dataDefinition.getDsname() + "'";

        try {
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(FTPCmd.SITE, dataDefinition.toString()))
                    && FTPReply.isPositiveCompletion(ftpClient.sendCommand(FTPCmd.CWD, "/"))
                    && FTPReply.isPositiveCompletion(ftpClient.sendCommand(FTPCmd.MKD, dataSetName))) {
                return;
            }
            throw new IOException(ftpClient.getReplyString());
        } catch (IOException e) {
            throw e;
        }
    }

    private void putFile() {
        //putMember cREA EL ARCHIVO PERO COMO vb  ---NECESITA DCB eso iria en este
        //método.
    }

    private FTPClient findFTPClient(String ftpServerName) {

        FTPClient ftpClient = ftpClientsMap.get(ftpServerName);

        if (ftpClient != null && ftpClient.isAvailable() && ftpClient.isConnected()) {
            return ftpClient;
        } else {
            FTPServer ftpServer = ftpServersMap.get(ftpServerName);
            ftpClient = createAsciiFTPClient(ftpServer);
            this.ftpClientsMap.put(ftpServerName, ftpClient);
            return ftpClient;
        }
    }

    private static FTPClient createAsciiFTPClient(FTPServer ftpServer) {

        String ip = ftpServer.getIp();
        Integer port = Integer.parseInt(ftpServer.getPort());
        String username = ftpServer.getUser();
        String password = ftpServer.getPassword();

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            boolean login = ftpClient.login(username, password);
            if (!login) {
                throw new IOException("User & password combination is invalid: "
                        + username
                        + " & "
                        + password
                        + ". ");
            }
            boolean setFileType = ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
            if (!setFileType) {
                throw new IOException("Server doesn't accept ASCII transfer mode. ");
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage()
                    + ". "
                    + "The FTPClient couldn't be created: "
                    + ftpServer.getServerName()
                    + ".");
        }

        return ftpClient;
    }

}
