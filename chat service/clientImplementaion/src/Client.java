import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Client {
    JFrame UI;
    Socket connection;
    ServerSocket server = null;
    OutputStream  outputStream = null;
    InputStream inputStream = null;
    public static Client instance;
    public Client(String host, int portNum) throws Exception{
        connection = new Socket(host, portNum);
        outputStream = connection.getOutputStream();
        inputStream = connection.getInputStream();
    }
    
    public static void main(String[] args) {
        new ClientLogIn();
    }
}
