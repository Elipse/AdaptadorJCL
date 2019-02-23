package mx.com.eixy.utilities.zos.ftp;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * <h1>This class provides a FTPClient to interact with Mainframe z/OS.</h1> 
 * <br>
 *  
 * 
 * @author Elí Álvarez Melgar <br>
 * 
 *
 */
public class huFtpClientFactory {

	private static final Map<String, FTPClient> listConnection = new HashMap<>();

	/**
	 * It creates or retrieves a ftp-connection.
	 * 
	 * @param environment
	 *            It represents the Mainframe's FTP server. <br>
	 *            Examples: INFODES, INFODE1, INFOPR1, INFOPRE, INFOPRO & BACKLOG.
	 * 
	 * 
	 * @return A persistent connection of type {@code FTPClient}.
	 */
	public static FTPClient fetchConnection(ServerConfig configConnection) throws SocketException, IOException {

		String environment = configConnection.getServerName();

		FTPClient ftpClient = listConnection.get(environment);

		if (ftpClient != null && ftpClient.isAvailable() && ftpClient.isConnected()) {
			return ftpClient;
		}

		ftpClient = createConnection(configConnection);

		listConnection.put(environment, ftpClient);

		return ftpClient;
	}
        
        public static Transfer fetchTransfer(ServerConfig configConnection) {
            
            return null;
        }

	private static FTPClient createConnection(ServerConfig configConnection) throws SocketException, IOException {
		FTPClient ftpClient = null;

		String server = configConnection.getIp();
		Integer port = Integer.parseInt(configConnection.getPort());
		String username = configConnection.getUser();
		String password = configConnection.getPassword();

		ftpClient = new FTPClient();		
		ftpClient.connect(server, port);
		ftpClient.login(username, password);
		ftpClient.setFileType(FTP.ASCII_FILE_TYPE);		

		return ftpClient;
	}
}