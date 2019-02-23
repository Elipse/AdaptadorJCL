package mx.com.eixy.utilities.zos.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;

/**
 * Solicita, a través de un FTPClient, la ejecución de los comandos de FTP
 * necesarios para subir o bajar archivos, desde y hacia el Mainframe z/OS.
 *
 * @author ELIALVA
 *
 */
public class Transfer {

    /**
     * It retrieves both member of pds and sequential file.
     *
     * @param environment It represents the Mainframe's FTP server. <br>
     * Examples: INFODES, INFODE1, INFOPR1, INFOPRE, INFOPRO & BACKLOG.
     * @param remoteFile This one can be a member file or a sequential file.<br>
     * Examples: DNCQP.BTCH.PROCLIB(QAD1000) or PGA.QAD2200.KTALOGO.
     * @param localFile The file to write in the local machine.
     * @throws IOException
     */
    public static void getFile(FTPClient ftpClient, String remoteFile, OutputStream localFile) throws IOException {

        remoteFile = "'" + remoteFile + "'";

        boolean rc = ftpClient.retrieveFile(remoteFile, localFile);
        if (rc == false) {
            throw new IOException(ftpClient.getReplyString());
        }
    }

    /**
     * It uploads both member of pds and sequential file.<br> *
     *
     * @param environment It represents the Mainframe's FTP server. <br>
     * Examples: INFODES, INFODE1, INFOPR1, INFOPRE, INFOPRO & BACKLOG.
     * @param remoteFile This one can be a member file or a sequential file.<br>
     * Examples: DNCQP.BTCH.PROCLIB(QAD1000) or PGA.QAD2200.KTALOGO.
     * @param localFile The file to read in the local machine.
     * @throws IOException
     */
    public static void putFile(FTPClient ftpClient, String remoteFile, InputStream localFile)
            throws IOException {

        remoteFile = "'" + remoteFile + "'";

        boolean rc = ftpClient.storeFile(remoteFile, localFile);

        if (rc == false) {
            throw new IOException(ftpClient.getReplyString());
        }
    }

    public static boolean exists(FTPClient ftpClient, String remoteFile) throws IOException {

        remoteFile = "'" + remoteFile + "'";

        try {
            ftpClient.changeWorkingDirectory(remoteFile);
            String[] i = ftpClient.listNames();
            return i == null ? false : true;
        } catch (IOException e) {
            throw new IOException(ftpClient.getReplyString());
        }
    }

    /**
     * It deletes and creates a PDS.
     *
     * @param environment It represents the Mainframe's FTP server. <br>
     * Examples: INFODES, INFODE1, INFOPR1, INFOPRE, INFOPRO & BACKLOG.
     * @param PDS This one can ONLY be the name of a partitioned data set.<br>
     * Examples: PXSTP.BTCH.PROCLIB.
     * @param props The properties to use in the creation of the partitioned
     * data set.
     * @throws IOException
     */
    public static void createPDS(FTPClient ftpClient, String PDS, PDSConfig pdsConfig) throws IOException {

        PDS = "'" + PDS + "'";

        try {
            ftpClient.sendCommand(FTPCmd.SITE, pdsConfig.toString());
            ftpClient.sendCommand(FTPCmd.CWD, "/");
            ftpClient.sendCommand(FTPCmd.MKD, PDS);
        } catch (IOException e) {
            throw new IOException(ftpClient.getReplyString());
        }
    }

}
