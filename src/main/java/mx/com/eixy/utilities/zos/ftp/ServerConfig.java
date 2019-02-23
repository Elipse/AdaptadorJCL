package mx.com.eixy.utilities.zos.ftp;

public class ServerConfig {

    private String serverName;
    private String ip;
    private String port;
    private String user;
    private String password;

    public String getServerName() {
        return serverName;
    }

    public ServerConfig setServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public ServerConfig setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ServerConfig setPort(String port) {
        this.port = port;
        return this;
    }

    public String getUser() {
        return user;
    }

    public ServerConfig setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ServerConfig setPassword(String password) {
        this.password = password;
        return this;
    }

}
